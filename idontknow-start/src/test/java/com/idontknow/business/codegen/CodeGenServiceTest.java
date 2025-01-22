package com.idontknow.business.codegen;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = TestConfig.class)
@ActiveProfiles("dev")
class CodeGenServiceTest {

    @Autowired
    private CodeGenService codeGenService;

    @Test
    void testGenerateEntity() throws Exception {
        // 准备测试数据
        String tableName = "base_container";
        String schema = "api";
        String outputPath = "target/generated-sources/java";

        // 执行代码生成
        String filePath = codeGenService.generateEntity(tableName, schema, outputPath);

        // 验证生成的文件
        Path path = Paths.get(filePath);
        assertTrue(Files.exists(path), "生成的文件应该存在");
    }
}
