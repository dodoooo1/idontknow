package com.idontknow.business.codegen;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseMetaService {

    private final JdbcClient jdbcClient;

    @Value("${spring.datasource.schema:public}")
    private String defaultSchema;

    public List<ColumnInfo> getTableColumns(String tableName, String schema) {
        String sql = """
            SELECT TABLE_NAME as tableName, COLUMN_NAME as columnName, COLUMN_DEFAULT as columnDefault, IS_NULLABLE as isNullable
                   , DATA_TYPE as dataType,
                   CHARACTER_MAXIMUM_LENGTH as characterMaximumLength
                 , COLUMN_COMMENT as columnComment, ORDINAL_POSITION as ordinalPosition
            FROM information_schema.columns 
            WHERE table_schema = :schema AND table_name = :table
            ORDER BY ordinal_position
        """;

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("schema", StringUtils.hasText(schema) ? schema : defaultSchema);
        parameters.addValue("table", tableName);

        List<ColumnInfo> columns = jdbcClient.sql(sql)
            .paramSource(parameters)
            .query(ColumnInfo.class)
            .list();

        if (columns.isEmpty()) {
            throw new IllegalArgumentException(
                String.format("表 %s.%s 不存在或没有列信息",
                    StringUtils.hasText(schema) ? schema : defaultSchema,
                    tableName));
        }

        return columns;
    }

    private String toCamelCase(String columnName) {
        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;

        for (char c : columnName.toLowerCase().toCharArray()) {
            if (c == '_') {
                nextUpper = true;
            } else {
                result.append(nextUpper ? Character.toUpperCase(c) : c);
                nextUpper = false;
            }
        }

        return result.toString();
    }
}
