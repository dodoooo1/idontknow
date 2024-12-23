package com.idontknow.business.adapter.base;

import com.idontknow.business.application.services.base.BaseService;
import com.idontknow.business.domain.entities.base.BaseEntity;
import com.idontknow.business.infra.assembler.base.SystemBaseMapper;
import jakarta.persistence.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

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
public abstract class BaseSystemController<E extends BaseEntity, C, U, R> {

    public abstract SystemBaseMapper<E, C, U, R> getMapper();

    public abstract BaseService<E> getService();


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<R> findById(@PathVariable("id") final Long id) {
        log.debug("[request] retrieve {} with id {}", this.getName(), id);
        final E entity = this.getService().findById(id);
        return ResponseEntity.ok(this.getMapper().toSystemResponse(entity));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") final Long id) {
        log.info("[request] delete {} with id {}", this.getName(), id);
        this.getService().delete(id);
        return ResponseEntity.noContent().build();
    }

    public String getName() {
        final Class<E> entityModelClass =
                (Class<E>)
                        ((ParameterizedType) this.getClass().getGenericSuperclass())
                                .getActualTypeArguments()[0];
        final Table annotation = entityModelClass.getAnnotation(Table.class);
        return annotation.name();
    }
}
