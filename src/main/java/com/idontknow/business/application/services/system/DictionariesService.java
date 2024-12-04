package com.idontknow.business.application.services.system;

import com.idontknow.business.application.services.base.BaseService;
import com.idontknow.business.application.services.system.dto.DictionariesResponse;
import com.idontknow.business.application.services.system.query.DefaultSearchSort;
import com.idontknow.business.application.services.system.query.SearchDictionariesFilter;
import com.idontknow.business.application.services.system.query.SearchDictionariesRequest;
import com.idontknow.business.constants.DateConstants;
import com.idontknow.business.domain.ability.DictionariesDomainService;
import com.idontknow.business.domain.entities.system.DictionaryEntity;
import com.idontknow.business.domain.entities.system.QDictionaryEntity;
import com.idontknow.business.infra.assembler.DictionariesMapper;
import com.idontknow.business.infra.gatewayimpl.repositories.DictionariesRepository;
import com.idontknow.business.shared.ApiListPaginationSuccess;
import com.querydsl.core.BooleanBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class DictionariesService extends BaseService<DictionaryEntity> {
    private final DictionariesMapper mapper;
    private final DictionariesDomainService domainService;
    private final DictionariesRepository repository;

    public ApiListPaginationSuccess<DictionariesResponse> getDictionaries(SearchDictionariesRequest request) {

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
        Page<DictionaryEntity> entities = repository.findAll(builder, pageable);
        List<DictionariesResponse> responses = entities.getContent().stream()
                .map(mapper::toSystemResponse)
                .collect(Collectors.toList());

        PageImpl<DictionariesResponse> responsePage = new PageImpl<>(responses, pageable, responses.size());
        return ApiListPaginationSuccess.of(responsePage);
    }

    private BooleanBuilder buildPredicate(SearchDictionariesRequest request) {
        QDictionaryEntity qUser = QDictionaryEntity.dictionaryEntity;
        BooleanBuilder builder = new BooleanBuilder();
        SearchDictionariesFilter filter = request.filter();
        if (Objects.isNull(filter)) {
            return builder;
            // throw  new RuntimeException("filter is null");
        }

        if (filter.createdAt() != null && filter.createdAt().startAt() != null) {
            builder.and(qUser.createdAt.goe(LocalDateTime.parse(filter.createdAt().startAt(), DateTimeFormatter.ofPattern(DateConstants.DATE_TIME_FORMAT))));
        }

        if (filter.createdAt() != null && filter.createdAt().endAt() != null) {
            builder.and(qUser.createdAt.loe(LocalDateTime.parse(filter.createdAt().endAt(), DateTimeFormatter.ofPattern(DateConstants.DATE_TIME_FORMAT))));
        }

        return builder;
    }
}
