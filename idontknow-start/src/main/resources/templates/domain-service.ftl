package ${packageName}.domain.ability;

import com.idontknow.business.domain.entities.${entityName?lower_case}.${entityName}Entity;
import com.idontknow.business.infra.gatewayimpl.repositories.${entityName}Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ${tableComment}领域服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ${entityName}DomainService {
    private final ${entityName}Repository repository;

    public ${entityName}Entity create(${entityName}Entity entity) {
        // 添加业务规则验证
        validateBusinessRules(entity);
        return repository.save(entity);
    }

    public ${entityName}Entity update(${entityName}Entity entity) {
        // 添加业务规则验证
        validateBusinessRules(entity);
        return repository.save(entity);
    }

    private void validateBusinessRules(${entityName}Entity entity) {
        // TODO: 添加业务规则验证
<#list fields as field>
    <#if !field.nullable>
        if (entity.get${field.name?cap_first}() == null) {
            throw new IllegalArgumentException("${field.comment}不能为空");
        }
    </#if>
</#list>
    }
} 