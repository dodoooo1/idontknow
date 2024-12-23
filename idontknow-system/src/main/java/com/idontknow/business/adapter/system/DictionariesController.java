package com.idontknow.business.adapter.system;

import com.idontknow.business.adapter.base.BaseSystemController;
import com.idontknow.business.application.services.system.DictionariesService;
import com.idontknow.business.application.services.system.dto.CreateDictionariesRequest;
import com.idontknow.business.application.services.system.dto.DictionariesResponse;
import com.idontknow.business.application.services.system.dto.UpdateDictionariesRequest;
import com.idontknow.business.application.services.system.query.SearchDictionariesRequest;
import com.idontknow.business.core.constants.AppUrls;
import com.idontknow.business.domain.entities.system.DictionaryEntity;
import com.idontknow.business.infra.assembler.DictionariesMapper;
import com.idontknow.business.jpa.ApiListPaginationSuccess;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(DictionariesController.BASE_URL)
@RequiredArgsConstructor
@Getter
public class DictionariesController extends BaseSystemController<
        DictionaryEntity,
        CreateDictionariesRequest,
        UpdateDictionariesRequest,
        DictionariesResponse> {
    public static final String BASE_URL = AppUrls.DICTIONARY;
    private final DictionariesService service;
    private final DictionariesMapper mapper;

    @GetMapping
    public ApiListPaginationSuccess<DictionariesResponse> getDictionaries(@RequestBody SearchDictionariesRequest request) {
        return service.getDictionaries(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<DictionariesResponse> create(@Valid @RequestBody final CreateDictionariesRequest request) {
        log.info("[request] create {}", request);
        final DictionaryEntity entity = service.create(mapper.toEntity(request));
        return ResponseEntity.ok(mapper.toSystemResponse(entity));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResponseEntity<DictionariesResponse> update(@PathVariable("id") final Long id, @Valid @RequestBody final UpdateDictionariesRequest request) {
        log.info("[request] update '{}' {}", id, request);
        final DictionaryEntity entity = service.findById(id);
        Assert.notNull(entity, "Role not found");
        Assert.isTrue(request.version() != entity.getVersion(), "Version mismatch");
        return ResponseEntity.ok(mapper.toSystemResponse(service.update(mapper.update(request, entity))));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public ResponseEntity<DictionariesResponse> patch(@PathVariable("id") final Long id, @RequestBody final UpdateDictionariesRequest request) {
        log.info("[request] patch  '{}' {}", id, request);
        final DictionaryEntity entity = service.findById(id);
        Assert.notNull(entity, "Role not found");
        Assert.isTrue(request.version() != entity.getVersion(), "Version mismatch");
        return ResponseEntity.ok(this.getMapper().toSystemResponse(service.update(mapper.update(request, entity))));
    }
}
