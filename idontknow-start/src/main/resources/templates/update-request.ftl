package ${packageName}.application.services.${entityName?lower_case}.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 更新${tableComment}请求
 */
public record Update${entityName}Request(
    @NotNull(message = "版本号不能为空")
    Integer version,
    
<#list fields as field>
    <#if field.type == "String">
    @Size(max = ${field.characterMaximumLength}, message = "${field.comment}长度不能超过${field.characterMaximumLength}个字符")
    </#if>
    ${field.type} ${field.name}<#if field_has_next>,</#if>
</#list>
) {}