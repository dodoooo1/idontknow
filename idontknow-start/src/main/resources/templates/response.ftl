package ${packageName}.application.services.${entityName?lower_case}.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

/**
 * ${tableComment}响应
 */
public record ${entityName}Response(
    Long id,
    Integer version,
<#list fields as field>
    <#if field.type == "LocalDateTime">
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    </#if>
    ${field.type} ${field.name}<#if field_has_next>,</#if>
</#list>
) {}