package com.idontknow.business.application.services.system;

import com.idontknow.business.application.services.system.dto.OperateLogResponse;
import com.idontknow.business.application.services.system.query.DefaultSearchSort;
import com.idontknow.business.application.services.system.query.SearchOperateLogFilter;
import com.idontknow.business.application.services.system.query.SearchOperateLogRequest;
import com.idontknow.business.constants.DateConstants;
import com.idontknow.business.domain.entities.system.OperateLogEntity;
import com.idontknow.business.domain.entities.system.QOperateLogEntity;
import com.idontknow.business.infra.assembler.OperateLogMapper;
import com.idontknow.business.infra.gatewayimpl.repositories.OperateLogRepository;
import com.idontknow.business.shared.ApiListPaginationSuccess;
import com.querydsl.core.BooleanBuilder;
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
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class OperateLogService {
    private final OperateLogMapper mapper;
    private final OperateLogRepository repository;

    public ApiListPaginationSuccess<OperateLogResponse> getOperateLog(SearchOperateLogRequest request) {

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

        Page<OperateLogEntity> entities = repository.findAll(builder, pageable);
        List<OperateLogResponse> responses = entities.getContent().stream()
                .map(mapper::toSystemResponse)
                .collect(Collectors.toList());

        PageImpl<OperateLogResponse> responsePage = new PageImpl<>(responses, pageable, responses.size());
        return ApiListPaginationSuccess.of(responsePage);
    }

    private BooleanBuilder buildPredicate(SearchOperateLogRequest request) {
        QOperateLogEntity qUser = QOperateLogEntity.operateLogEntity;
        BooleanBuilder builder = new BooleanBuilder();
        SearchOperateLogFilter filter = request.filter();
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
