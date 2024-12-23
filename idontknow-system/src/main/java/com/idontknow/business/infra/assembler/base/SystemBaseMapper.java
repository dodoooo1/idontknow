package com.idontknow.business.infra.assembler.base;

import com.idontknow.business.infra.assembler.annotations.ToEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Collection;

/**
 * The interface Base mapper management.
 *
 * @param <E> the type parameter Entity
 * @param <C> the type parameter CreateRequest
 * @param <U> the type parameter UpdateRequest
 * @param <R> the type parameter Response
 */
public interface SystemBaseMapper<E, C, U, R> {

    @ToEntity
    E toEntity(C request);

    @ToEntity
    E update(U request, @MappingTarget E entity);

    @ToEntity
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    E patch(U request, @MappingTarget E entity);

    R toSystemResponse(E entity);

    Collection<R> toSystemResponse(Collection<E> entity);
}
