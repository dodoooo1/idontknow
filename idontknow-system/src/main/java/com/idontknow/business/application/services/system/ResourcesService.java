package com.idontknow.business.application.services.system;

import com.idontknow.business.application.services.base.BaseService;
import com.idontknow.business.application.services.system.dto.ResourcesResponse;
import com.idontknow.business.application.services.system.query.DefaultSearchSort;
import com.idontknow.business.application.services.system.query.SearchResourcesFilter;
import com.idontknow.business.application.services.system.query.SearchResourcesRequest;
import com.idontknow.business.core.constants.DateConstants;
import com.idontknow.business.domain.ability.ResourcesDomainService;
import com.idontknow.business.domain.entities.system.QResourceEntity;
import com.idontknow.business.domain.entities.system.ResourceEntity;
import com.idontknow.business.infra.assembler.ResourcesMapper;
import com.idontknow.business.infra.gatewayimpl.repositories.ResourcesRepository;
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
 * @description:
 * @title: UserService
 * @package com.idontknow.business.application.services.query
 * @author: glory
 * @date: 2024/10/23 16:00
 */
@Slf4j
@Service
@Getter
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ResourcesService extends BaseService<ResourceEntity> {
    private final ResourcesMapper mapper;
    private final ResourcesRepository repository;
    private final ResourcesDomainService domainService;

    public ApiListPaginationSuccess<ResourcesResponse> getResources(SearchResourcesRequest request) {
        SearchResourcesFilter filter = request.filter();
        if (Objects.isNull(filter)) {
            return ApiListPaginationSuccess.of(Page.empty());
        }
        BooleanBuilder builder = buildPredicate(filter);
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

        Page<ResourceEntity> entities = repository.findAll(builder, pageable);
        List<ResourcesResponse> responses = entities.getContent().stream()
                .map(mapper::toSystemResponse)
                .collect(Collectors.toList());

        PageImpl<ResourcesResponse> responsePage = new PageImpl<>(responses, pageable, responses.size());
        return ApiListPaginationSuccess.of(responsePage);
    }

    private BooleanBuilder buildPredicate(SearchResourcesFilter filter) {
        QResourceEntity qResource = QResourceEntity.resourceEntity;
        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasLength(filter.name())) {
            builder.and(qResource.name.containsIgnoreCase(filter.name()));
        }

        if (filter.createdAt() != null && filter.createdAt().startAt() != null) {
            builder.and(qResource.createdAt.goe(LocalDateTime.parse(filter.createdAt().startAt(), DateTimeFormatter.ofPattern(DateConstants.DATE_TIME_FORMAT))));
        }

        if (filter.createdAt() != null && filter.createdAt().endAt() != null) {
            builder.and(qResource.createdAt.loe(LocalDateTime.parse(filter.createdAt().endAt(), DateTimeFormatter.ofPattern(DateConstants.DATE_TIME_FORMAT))));
        }

        builder.and(qResource.deleted.eq(false));
        return builder;
    }

    public ResourceEntity create(ResourceEntity entity) {
        return domainService.create(entity);
    }

    public ResourceEntity update(ResourceEntity update) {
        return domainService.update(update);
    }
}
