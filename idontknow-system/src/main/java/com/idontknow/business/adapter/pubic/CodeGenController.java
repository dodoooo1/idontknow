package com.idontknow.business.adapter.pubic;

import com.idontknow.business.codegen.CodeGenService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Getter
@RequestMapping("/api/codegen")
@RequiredArgsConstructor
public class CodeGenController {

    private final CodeGenService codeGenService;

    @GetMapping("/generate")
    public ResponseEntity<String> generateEntity(
            @RequestParam String tableName,
            @RequestParam(required = false, defaultValue = "public") String schema,
            @RequestParam(required = false) String outputPath) {
        try {
            String generatedPath = codeGenService.generateEntity(tableName, schema, outputPath);
            return ResponseEntity.ok("代码生成成功，文件路径：" + generatedPath);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("代码生成失败：" + e.getMessage());
        }
    }
}
