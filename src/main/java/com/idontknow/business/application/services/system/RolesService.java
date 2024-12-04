package com.idontknow.business.application.services.system;

import com.idontknow.business.application.services.base.BaseService;
import com.idontknow.business.application.services.system.dto.RolesResponse;
import com.idontknow.business.application.services.system.query.DefaultSearchSort;
import com.idontknow.business.application.services.system.query.SearchRolesFilter;
import com.idontknow.business.application.services.system.query.SearchRolesRequest;
import com.idontknow.business.constants.DateConstants;
import com.idontknow.business.domain.ability.RolesDomainService;
import com.idontknow.business.domain.entities.system.QRoleEntity;
import com.idontknow.business.domain.entities.system.RoleEntity;
import com.idontknow.business.infra.assembler.RolesMapper;
import com.idontknow.business.infra.gatewayimpl.repositories.RolesRepository;
import com.idontknow.business.shared.ApiListPaginationSuccess;
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
@RequiredArgsConstructor
@Getter
@Transactional(rollbackFor = Exception.class)
public class RolesService extends BaseService<RoleEntity> {
    private final RolesMapper mapper;
    private final RolesRepository repository;
    private final RolesDomainService domainService;

    public ApiListPaginationSuccess<RolesResponse> getRoles(SearchRolesRequest request) {
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
        Page<RoleEntity> entities = repository.findAll(builder, pageable);
        List<RolesResponse> responses = entities.getContent().stream()
                .map(mapper::toSystemResponse)
                .collect(Collectors.toList());

        PageImpl<RolesResponse> responsePage = new PageImpl<>(responses, pageable, responses.size());
        return ApiListPaginationSuccess.of(responsePage);
    }

    private BooleanBuilder buildPredicate(SearchRolesRequest request) {
        QRoleEntity qRole = QRoleEntity.roleEntity;
        BooleanBuilder builder = new BooleanBuilder();
        SearchRolesFilter filter = request.filter();
        if (Objects.isNull(filter)) {
            return builder;
            // throw  new RuntimeException("filter is null");
        }
        if (StringUtils.hasLength(filter.name())) {
            builder.and(qRole.name.containsIgnoreCase(filter.name()));
        }
        if (filter.createdAt() != null && filter.createdAt().startAt() != null) {
            builder.and(qRole.createdAt.goe(LocalDateTime.parse(filter.createdAt().startAt(), DateTimeFormatter.ofPattern(DateConstants.DATE_TIME_FORMAT))));
        }
        if (filter.createdAt() != null && filter.createdAt().endAt() != null) {
            builder.and(qRole.createdAt.loe(LocalDateTime.parse(filter.createdAt().endAt(), DateTimeFormatter.ofPattern(DateConstants.DATE_TIME_FORMAT))));
        }

        builder.and(qRole.deleted.eq(false));
        return builder;
    }

}
