package com.idontknow.business.application.services;

/**
 * @description:
 * @title: BaseService
 * @package com.idontknow.business.application.services
 * @author: glory
 * @date: 2024/10/24 10:29
 */

import com.idontknow.business.exceptions.ResourceNotFoundException;
import com.idontknow.business.infra.event.listeners.EntityTransactionLogListener;
import com.idontknow.business.infra.gatewayimpl.dataobject.base.BaseEntity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.lang.String.format;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Slf4j
@Transactional(readOnly = true)
public abstract class BaseService<E> {


    @Autowired public ApplicationEventPublisher applicationEventPublisher;

}

