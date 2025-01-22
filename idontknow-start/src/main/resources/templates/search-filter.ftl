package ${packageName}.application.services.${entityName?lower_case}.query;

import com.idontknow.business.core.query.DateTimeRange;

/**
 * ${tableComment}查询过滤器
 */
public record Search${entityName}Filter(
<#list fields as field>
    <#if field.type == "LocalDateTime">
    DateTimeRange ${field.name}<#if field_has_next>,</#if>
    <#else>
    ${field.type} ${field.name}<#if field_has_next>,</#if>
    </#if>
</#list>
) {} 