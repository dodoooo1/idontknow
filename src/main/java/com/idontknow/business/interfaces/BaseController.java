package com.idontknow.business.interfaces;

import com.idontknow.business.application.services.BaseService;
import com.idontknow.business.infra.assembler.base.BaseMapper;
import com.idontknow.business.infra.gatewayimpl.dataobject.base.BaseEntity;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;

/**
 * The type Base management controller.
 *
 * @param <E> the type parameter Entity
 * @param <C> the type parameter CreateRequest
 * @param <U> the type parameter UpdateRequest
 * @param <R> the type parameter Response
 */
@Slf4j
public abstract class BaseController<E ,D extends BaseEntity, C, U, R> {

  public abstract BaseMapper<E,D, C, U, R> getMapper();

}
