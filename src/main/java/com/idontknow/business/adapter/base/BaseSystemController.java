package com.idontknow.business.adapter.base;

import com.idontknow.business.application.services.base.BaseService;
import com.idontknow.business.domain.ability.base.BaseDomainService;
import com.idontknow.business.domain.entities.base.BaseEntity;
import com.idontknow.business.infra.assembler.base.SystemBaseMapper;
import com.idontknow.business.shared.ApiListPaginationSuccess;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
/*

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public ApiListPaginationSuccess<R> findAll(final S pageable) {
    log.debug("[request] retrieve all {}", this.getName());
    final Page<E> entities = this.getService().findAll(pageable);
    final Page<R> response = entities.map(this.getMapper()::toSystemResponse);
    return ApiListPaginationSuccess.of(response);
  }
*/

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<R> create(@Valid @RequestBody final C request) {
        log.info("[request] create {}", request);
        final E entity = this.getService().create(this.getMapper().toEntity(request));
        return ResponseEntity.ok(this.getMapper().toSystemResponse(entity));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResponseEntity<R> update(@PathVariable("id") final Long id, @Valid @RequestBody final U request) {
        log.info("[request] update '{}' {}", id, request);

        final E original = this.getService().findById(id);
        final E merged = this.getMapper().update(request, original);
        final E entity = this.getService().update(merged);

        return ResponseEntity.ok(this.getMapper().toSystemResponse(entity));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public ResponseEntity<R> patch(@PathVariable("id") final Long id, @RequestBody final U request) {
        log.info("[request] patch  '{}' {}", id, request);

        final E original = this.getService().findById(id);
        final E merged = this.getMapper().patch(request, original);
        final E entity = this.getService().update(merged);

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
