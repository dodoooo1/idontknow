package com.idontknow.business.codegen;

import lombok.Builder;
import lombok.Data;
import java.util.*;

@Data
@Builder
public class EntityTemplateBuilder {
    
    @Data
    @Builder
    public static class TemplateData {
        private String packageName;
        private String className;
        private String tableName;
        private String tableComment;
        private String author;
        private Set<String> imports;
        private List<FieldInfo> fields;
    }
    
    @Data
    @Builder
    public static class FieldInfo {
        private String name;
        private String type;
        private String columnName;
        private String comment;
        private boolean nullable;
    }
    
    public static TemplateData buildFromColumns(List<ColumnInfo> columns) {
        if (columns == null || columns.isEmpty()) {
            throw new IllegalArgumentException("列信息不能为空");
        }
        
        // 获取表名和类名
        String tableName = columns.get(0).getTableName();
        String className = toClassName(tableName);
        
        // 构建导入列表
        Set<String> imports = new HashSet<>(Arrays.asList(
            "com.idontknow.business.domain.entities.base.BaseEntity",
            "jakarta.persistence.*",
            "lombok.AllArgsConstructor",
            "lombok.Getter",
            "lombok.NoArgsConstructor",
            "lombok.Setter",
            "lombok.experimental.SuperBuilder",
            "org.springframework.data.jpa.domain.support.AuditingEntityListener"
        ));
        
        // 构建字段信息
        List<FieldInfo> fields = new ArrayList<>();
        for (ColumnInfo column : columns) {
            // 跳过ID字段，因为已经在基类中定义
            if ("id".equalsIgnoreCase(column.getColumnName())) {
                continue;
            }
            
            String javaType = column.getJavaType();
            // 如果是特殊类型，添加对应的import
            if (javaType.contains(".")) {
                imports.add(javaType);
            }
            
            fields.add(FieldInfo.builder()
                .name(column.getPropertyName())
                .type(javaType.substring(javaType.lastIndexOf('.') + 1))
                .columnName(column.getColumnName())
                .comment(column.getColumnComment())
                .nullable("YES".equalsIgnoreCase(column.getIsNullable()))
                .build());
        }
        
        return TemplateData.builder()
            .packageName("com.idontknow.business.domain.entities")
            .className(className)
            .tableName(tableName)
            .tableComment("数据库表" + tableName + "的实体类")
            .author("系统自动生成")
            .imports(imports)
            .fields(fields)
            .build();
    }
    
    private static String toClassName(String tableName) {
        // 移除表前缀（如sys_）
        String nameWithoutPrefix = tableName.replaceAll("^[a-z]+_", "");
        // 转换为驼峰命名
        StringBuilder className = new StringBuilder();
        boolean nextUpper = true;
        for (char c : nameWithoutPrefix.toCharArray()) {
            if (c == '_') {
                nextUpper = true;
            } else {
                className.append(nextUpper ? Character.toUpperCase(c) : c);
                nextUpper = false;
            }
        }
        return className.toString() + "Entity";
    }
} 