package com.idontknow.business.infra.assembler.base;

import com.idontknow.business.infra.assembler.annotations.ToDataObject;
import com.idontknow.business.infra.assembler.annotations.ToEntity;
import org.checkerframework.checker.units.qual.C;
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
public interface BaseMapper<E, D,C, U, R> {

  @ToEntity
  E toEntity(C request);
  @ToDataObject
  D ToDataObject(E entity);
  @ToEntity
  E update(U request, @MappingTarget E entity);

  @ToEntity
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  E patch(U request, @MappingTarget E entity);

  R toResponse(E entity);
  R dataObjectToResponse(D dataObject);

  Collection<R> toResponse(Collection<E> entity);
}
