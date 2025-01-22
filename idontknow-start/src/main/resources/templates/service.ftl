package ${packageName}.application.services.${entityName?lower_case};

import com.idontknow.business.application.services.base.BaseService;
import com.idontknow.business.application.services.${entityName?lower_case}.dto.${entityName}Response;
import com.idontknow.business.application.services.${entityName?lower_case}.query.DefaultSearchSort;
import com.idontknow.business.application.services.${entityName?lower_case}.query.Search${entityName}Filter;
import com.idontknow.business.application.services.${entityName?lower_case}.query.Search${entityName}Request;
import com.idontknow.business.core.constants.DateConstants;
import com.idontknow.business.domain.ability.${entityName}DomainService;
import com.idontknow.business.domain.entities.${entityName?lower_case}.Q${entityName}Entity;
import com.idontknow.business.domain.entities.${entityName?lower_case}.${entityName}Entity;
import com.idontknow.business.infra.assembler.${entityName}Mapper;
import com.idontknow.business.infra.gatewayimpl.repositories.${entityName}Repository;
import com.idontknow.business.jpa.ApiListPaginationSuccess;
import com.querydsl.core.BooleanBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ${tableComment}服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Getter
@Transactional(rollbackFor = Exception.class)
public class ${entityName}Service extends BaseService<${entityName}Entity> {
    private final ${entityName}Mapper mapper;
    private final ${entityName}Repository repository;
    private final ${entityName}DomainService domainService;

    public ApiListPaginationSuccess<${entityName}Response> get${entityName}s(Search${entityName}Request request) {
        BooleanBuilder builder = buildPredicate(request);
        DefaultSearchSort sorted = request.sort();
        if (Objects.isNull(sorted)) {
            sorted = new DefaultSearchSort("updatedAt", "DESC");
        }
        Sort sort;
        if (sorted.sortOrder().equalsIgnoreCase("ASC")) {
            sort = Sort.by(Sort.Direction.ASC, sorted.sortField());
        } else {
            sort = Sort.by(Sort.Direction.DESC, sorted.sortField());
        }

        Pageable pageable = PageRequest.of(request.number() - 1, request.size(), sort);
        Page<${entityName}Entity> entities = repository.findAll(builder, pageable);
        List<${entityName}Response> responses = entities.getContent().stream()
                .map(mapper::toSystemResponse)
                .collect(Collectors.toList());

        PageImpl<${entityName}Response> responsePage = new PageImpl<>(responses, pageable, responses.size());
        return ApiListPaginationSuccess.of(responsePage);
    }

    private BooleanBuilder buildPredicate(Search${entityName}Request request) {
        Q${entityName}Entity q${entityName} = Q${entityName}Entity.${entityName?uncap_first}Entity;
        BooleanBuilder builder = new BooleanBuilder();
        Search${entityName}Filter filter = request.filter();
        if (Objects.isNull(filter)) {
            return builder;
        }

<#list fields as field>
    <#if field.type == "String">
        if (StringUtils.hasLength(filter.${field.name}())) {
            builder.and(q${entityName}.${field.name}.containsIgnoreCase(filter.${field.name}()));
        }
    <#elseif field.type == "LocalDateTime">
        if (filter.${field.name}() != null && filter.${field.name}().startAt() != null) {
            builder.and(q${entityName}.${field.name}.goe(LocalDateTime.parse(filter.${field.name}().startAt(), DateTimeFormatter.ofPattern(DateConstants.DATE_TIME_FORMAT))));
        }
        if (filter.${field.name}() != null && filter.${field.name}().endAt() != null) {
            builder.and(q${entityName}.${field.name}.loe(LocalDateTime.parse(filter.${field.name}().endAt(), DateTimeFormatter.ofPattern(DateConstants.DATE_TIME_FORMAT))));
        }
    <#else>
        if (filter.${field.name}() != null) {
            builder.and(q${entityName}.${field.name}.eq(filter.${field.name}()));
        }
    </#if>
</#list>

        builder.and(q${entityName}.deleted.eq(false));
        return builder;
    }

    public ${entityName}Entity create(${entityName}Entity entity) {
        return domainService.create(entity);
    }

    public ${entityName}Entity update(${entityName}Entity entity) {
        return domainService.update(entity);
    }
} 