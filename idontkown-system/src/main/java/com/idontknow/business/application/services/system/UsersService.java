package com.idontknow.business.application.services.system;

import com.idontknow.business.application.services.base.BaseService;
import com.idontknow.business.application.services.system.dto.UsersResponse;
import com.idontknow.business.application.services.system.query.DefaultSearchSort;
import com.idontknow.business.application.services.system.query.SearchUsersFilter;
import com.idontknow.business.application.services.system.query.SearchUsersRequest;
import com.idontknow.business.constants.DateConstants;
import com.idontknow.business.domain.ability.UsersDomainService;
import com.idontknow.business.domain.entities.system.QUserEntity;
import com.idontknow.business.domain.entities.system.UserEntity;
import com.idontknow.business.infra.assembler.UsersMapper;
import com.idontknow.business.infra.gatewayimpl.repositories.UsersRepository;
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
@Getter
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UsersService extends BaseService<UserEntity> {
    private final UsersMapper mapper;
    private final UsersRepository repository;
    private final UsersDomainService domainService;


    public ApiListPaginationSuccess<UsersResponse> getUsers(SearchUsersRequest request) {

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

        //List<UserEntity> dnUserEntity = queryFactory.select(QUserEntity.userEntity).from(QUserEntity.userEntity).where(builder).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        Page<UserEntity> entities = repository.findAll(builder, pageable);
        List<UsersResponse> responses = entities.getContent().stream()
                .map(mapper::toSystemResponse)
                .collect(Collectors.toList());

        PageImpl<UsersResponse> responsePage = new PageImpl<>(responses, pageable, responses.size());
        return ApiListPaginationSuccess.of(responsePage);
    }

    private BooleanBuilder buildPredicate(SearchUsersRequest request) {
        QUserEntity qUser = QUserEntity.userEntity;
        BooleanBuilder builder = new BooleanBuilder();
        SearchUsersFilter filter = request.filter();
        if (Objects.isNull(filter)) {
            return builder;
            // throw  new RuntimeException("filter is null");
        }
        if (StringUtils.hasLength(filter.name())) {
            builder.and(qUser.name.containsIgnoreCase(filter.name()));
        }

        if (StringUtils.hasLength(filter.username())) {
            builder.and(qUser.username.eq(filter.username()));
        }

        if (StringUtils.hasLength(filter.email())) {
            builder.and(qUser.email.eq(filter.email()));
        }

        if (filter.createdAt() != null && filter.createdAt().startAt() != null) {
            builder.and(qUser.createdAt.goe(LocalDateTime.parse(filter.createdAt().startAt(), DateTimeFormatter.ofPattern(DateConstants.DATE_TIME_FORMAT))));
        }

        if (filter.createdAt() != null && filter.createdAt().endAt() != null) {
            builder.and(qUser.createdAt.loe(LocalDateTime.parse(filter.createdAt().endAt(), DateTimeFormatter.ofPattern(DateConstants.DATE_TIME_FORMAT))));
        }

        if (filter.status() != null && StringUtils.hasLength(filter.status().getValue())) {
            builder.and(qUser.status.eq(filter.status()));
        }

        return builder;
    }

    public boolean matchesPassword(String loginPassword, String password) {
        return domainService.matchesPassword(loginPassword, password);
    }


    public UserEntity create(UserEntity entity) {
        return domainService.create(entity);
    }

    public UserEntity update(UserEntity update) {
       return domainService.update(update);
    }
}
