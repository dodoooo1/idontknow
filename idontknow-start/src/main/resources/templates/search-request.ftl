package ${packageName}.application.services.${entityName?lower_case}.query;

import com.idontknow.business.core.query.BaseSearchRequest;
import com.idontknow.business.core.query.SearchField;
import com.idontknow.business.core.query.SearchOperation;

/**
 * 查询${tableComment}请求
 */
public record Search${entityName}Request(
<#list fields as field>
    <#if field.type == "String">
    @SearchField(operation = SearchOperation.LIKE)
    <#else>
    @SearchField(operation = SearchOperation.EQUALS)
    </#if>
    ${field.type} ${field.name}<#if field_has_next>,</#if>
</#list>
) extends BaseSearchRequest {}