package com.idontknow.business.application.services.system.impl;

import com.idontknow.business.application.services.system.UsersService;
import com.idontknow.business.application.services.system.dto.*;
import com.idontknow.business.constants.DateConstants;
import com.idontknow.business.domain.ability.UserDomainService;
import com.idontknow.business.domain.entities.system.QUserEntity;
import com.idontknow.business.domain.entities.system.UserEntity;
import com.idontknow.business.infra.assembler.UserEntityMapper;
import com.idontknow.business.infra.gatewayimpl.repositories.UsersRepository;
import com.idontknow.business.shared.ApiListPaginationSimple;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
@Transactional(rollbackFor = Exception.class)
public class UsersServiceImpl implements UsersService {
    private final UserEntityMapper mapper;
    private final UserDomainService userDomainService;
    private final UsersRepository repository;
   // private final JPAQueryFactory  queryFactory;

    public ApiListPaginationSimple<UserEntityResponse> listByPage(SearchUsersRequest request) {

        BooleanBuilder builder = buildPredicate(request);

        SearchUsersSort sorted = request.sort();
        if (Objects.isNull(sorted)){
            sorted=  new SearchUsersSort("updatedAt","DESC");
        }
        Sort sort ;
        if (sorted.sortOrder().equalsIgnoreCase("ASC")){
            sort = Sort.by(Sort.Direction.ASC, sorted.sortField());
        }else {
            sort = Sort.by(Sort.Direction.DESC, sorted.sortField());
        }

        Pageable pageable = PageRequest.of(request.number()-1, request.size(),sort);

        //List<UserEntity> dnUserEntity = queryFactory.select(QUserEntity.userEntity).from(QUserEntity.userEntity).where(builder).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        Page<UserEntity> entities = repository.findAll(builder, pageable);
        List<UserEntityResponse> responses = entities.getContent().stream()
                .map(mapper::toSystemResponse)
                .collect(Collectors.toList());

        PageImpl<UserEntityResponse> responsePage = new PageImpl<>(responses, pageable, responses.size());
        return  ApiListPaginationSimple.of(responsePage);
    }

    private BooleanBuilder buildPredicate(SearchUsersRequest request) {
        QUserEntity qUser = QUserEntity.userEntity;
        BooleanBuilder builder = new BooleanBuilder();
        SearchUsersFilter filter = request.filter();
        if (Objects.isNull(filter)){
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

        if (filter.createdAt()!=null && filter.createdAt().startAt() != null) {
            builder.and(qUser.createdAt.goe(LocalDateTime.parse(filter.createdAt().startAt(),DateTimeFormatter.ofPattern(DateConstants.DATE_TIME_FORMAT))));
        }

        if (filter.createdAt()!=null&& filter.createdAt().endAt() != null) {
            builder.and(qUser.createdAt.loe(LocalDateTime.parse(filter.createdAt().endAt(),DateTimeFormatter.ofPattern(DateConstants.DATE_TIME_FORMAT))));
        }

        if (filter.status()!=null && StringUtils.hasLength(filter.status().getValue())) {
            builder.and(qUser.status.eq(filter.status()));
        }

        return builder;
    }

    @Override
    public UserEntity create(UserEntity userEntity) {
        return userDomainService.create(userEntity);
    }


    @Override
    public UserEntity findById(String id) {
        return userDomainService.findById(Long.valueOf(id));
    }

    /**
     * @param updateUsersRequest
     */
    @Override
    public void updateStatus(UpdateUsersRequest updateUsersRequest) {
        UserEntity dbEntity = userDomainService.findById(Long.valueOf(updateUsersRequest.id()));
        if (dbEntity == null) {
            throw new RuntimeException("User not found");
        }
        ;
        dbEntity.updateStatus(updateUsersRequest.updateStatusRequest().status());
        userDomainService.update(dbEntity);
    }

    @Override
    public UserEntity loadUserByUsername(String username) {
        return userDomainService.loadUserByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void update(UpdateUsersRequest updateUsersRequest) {
        UserEntity dbEntity = userDomainService.findById(Long.valueOf(updateUsersRequest.id()));
        userDomainService.update(mapper.update(updateUsersRequest, dbEntity));
    }

    @Override
    public void delete(String id) {
        userDomainService.delete(Long.valueOf(id));
    }

    @Transactional(rollbackFor = Exception.class)
    public void bulkDeleteUsers(List<Long> userIds) {
        userDomainService.bulkDeleteUsers(userIds);
    }

    @Override
    public boolean matchesPassword(String loginPassword, String password) {
        return userDomainService.matchesPassword(loginPassword, password);
    }

    @Override
    public void updatePassword(UpdateUsersRequest updateUsersRequest) {
        UserEntity dbEntity = userDomainService.findById(Long.valueOf(updateUsersRequest.id()));
        userDomainService.updatePassword(mapper.update(updateUsersRequest, dbEntity));
    }
}
