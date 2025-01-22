package com.idontknow.business.codegen;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CodeGenService {

    private final Configuration freemarkerConfiguration;
    private final DatabaseMetaService databaseMetaService;

    @Value("${codegen.default.output-path:src/main/java}")
    private String defaultOutputPath;

    private static final Map<String, String> TEMPLATES = Map.ofEntries(
        Map.entry("entity", "entity.ftl"),
        Map.entry("controller", "controller.ftl"),
        Map.entry("service", "service.ftl"),
        Map.entry("domain-service", "domain-service.ftl"),
        Map.entry("repository", "repository.ftl"),
        Map.entry("create-request", "create-request.ftl"),
        Map.entry("update-request", "update-request.ftl"),
        Map.entry("response", "response.ftl"),
        Map.entry("search-request", "search-request.ftl"),
        Map.entry("search-filter", "search-filter.ftl"),
        Map.entry("mapper", "mapper.ftl")
    );

    public String generateEntity(String tableName, String schema, String outputPath) throws Exception {
        // 获取表的列信息
        List<ColumnInfo> columns = databaseMetaService.getTableColumns(tableName, schema);

        // 构建模板数据
        EntityTemplateBuilder.TemplateData templateData =
            EntityTemplateBuilder.buildFromColumns(columns);

        // 生成所有文件
        String finalOutputPath = outputPath != null ? outputPath : defaultOutputPath;
        
        for (Map.Entry<String, String> entry : TEMPLATES.entrySet()) {
            String type = entry.getKey();
            String templateName = entry.getValue();
            
            // 确定输出路径
            String subPackage = getSubPackage(type);
            String packagePath = templateData.getPackageName() + subPackage;
            packagePath = packagePath.replace('.', '/');
            Path fullPath = Paths.get(finalOutputPath, packagePath);

            // 创建目录
            Files.createDirectories(fullPath);

            // 生成文件
            String className = getClassName(type, templateData.getClassName());
            File outputFile = new File(fullPath.toFile(), className + ".java");

            // 使用FreeMarker渲染模板
            Template template = freemarkerConfiguration.getTemplate(templateName);
            try (FileWriter writer = new FileWriter(outputFile)) {
                template.process(templateData, writer);
            }
        }

        return finalOutputPath;
    }
    
    private String getSubPackage(String type) {
        return switch (type) {
            case "entity" -> ".domain.entities";
            case "controller" -> ".adapter";
            case "service" -> ".application.services";
            case "domain-service" -> ".domain.ability";
            case "repository" -> ".infra.gatewayimpl.repositories";
            case "create-request", "update-request", "response" -> ".application.services.dto";
            case "search-request", "search-filter" -> ".application.services.query";
            case "mapper" -> ".infra.assembler";
            default -> "";
        };
    }
    
    private String getClassName(String type, String entityName) {
        return switch (type) {
            case "entity" -> entityName + "Entity";
            case "controller" -> entityName + "Controller";
            case "service" -> entityName + "Service";
            case "domain-service" -> entityName + "DomainService";
            case "repository" -> entityName + "Repository";
            case "create-request" -> "Create" + entityName + "Request";
            case "update-request" -> "Update" + entityName + "Request";
            case "response" -> entityName + "Response";
            case "search-request" -> "Search" + entityName + "Request";
            case "search-filter" -> "Search" + entityName + "Filter";
            case "mapper" -> entityName + "Mapper";
            default -> entityName;
        };
    }
}
