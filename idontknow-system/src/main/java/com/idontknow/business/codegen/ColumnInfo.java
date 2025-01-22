package com.idontknow.business.codegen;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class ColumnInfo {
    private String tableName;
    private String columnName;
    private String dataType;
    private String columnDefault;
    private String columnComment;
    private String isNullable;
    private Long characterMaximumLength;
    private Integer ordinalPosition;

    public static String getJavaType(String sqlType) {
        Map<String, String> typeMap = new HashMap<>();
        typeMap.put("VARCHAR", "String");
        typeMap.put("CHAR", "String");
        typeMap.put("INT", "Integer");
        typeMap.put("BIGINT", "Long");
        typeMap.put("TINYINT", "Boolean");
        typeMap.put("DATE", "java.util.Date");
        typeMap.put("DATETIME", "java.util.Date");
        typeMap.put("TIMESTAMP", "java.util.Date");
        typeMap.put("DECIMAL", "java.math.BigDecimal");
        typeMap.put("DOUBLE", "Double");
        typeMap.put("FLOAT", "Float");

        return typeMap.getOrDefault(sqlType.toUpperCase(), "Object");
    }

    public String getJavaType() {
        return getJavaType(this.dataType);
    }

    public String getPropertyName() {
        return toCamelCase(columnName);
    }

    private String toCamelCase(String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = false;
        for (char c : input.toLowerCase().toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                titleCase.append(Character.toTitleCase(c));
                nextTitleCase = false;
            } else {
                titleCase.append(c);
            }
        }
        return titleCase.toString();
    }
}
