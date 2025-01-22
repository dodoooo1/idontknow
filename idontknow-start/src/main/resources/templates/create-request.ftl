package ${packageName}.application.services.${entityName?lower_case}.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 创建${tableComment}请求
 */
public record Create${entityName}Request(
<#list fields as field>
    <#if !field.nullable>
    <#if field.type == "String">
    @NotBlank(message = "${field.comment}不能为空")
    @Size(max = ${field.characterMaximumLength}, message = "${field.comment}长度不能超过${field.characterMaximumLength}个字符")
    <#else>
    @NotNull(message = "${field.comment}不能为空")
    </#if>
    </#if>
    ${field.type} ${field.name}<#if field_has_next>,</#if>
</#list>
) {}