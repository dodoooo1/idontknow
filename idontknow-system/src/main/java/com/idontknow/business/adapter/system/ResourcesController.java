package com.idontknow.business.adapter.system;

import com.idontknow.business.adapter.base.BaseSystemController;
import com.idontknow.business.application.services.system.ResourcesService;
import com.idontknow.business.application.services.system.dto.CreateResourcesRequest;
import com.idontknow.business.application.services.system.dto.ResourcesResponse;
import com.idontknow.business.application.services.system.dto.UpdateResourcesRequest;
import com.idontknow.business.application.services.system.query.SearchResourcesRequest;
import com.idontknow.business.core.constants.AppUrls;
import com.idontknow.business.domain.entities.system.ResourceEntity;
import com.idontknow.business.infra.assembler.ResourcesMapper;
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
@RequestMapping(ResourcesController.BASE_URL)
@RequiredArgsConstructor
@Getter
public class ResourcesController extends BaseSystemController<
        ResourceEntity,
        CreateResourcesRequest,
        UpdateResourcesRequest,
        ResourcesResponse> {
    public static final String BASE_URL = AppUrls.RESOURCES;
    private final ResourcesService service;
    private final ResourcesMapper mapper;

    @GetMapping
    public ApiListPaginationSuccess<ResourcesResponse> getResources(@RequestBody SearchResourcesRequest request) {
        return service.getResources(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<ResourcesResponse> create(@Valid @RequestBody final CreateResourcesRequest request) {
        log.info("[request] create {}", request);
        final ResourceEntity entity = service.create(mapper.toEntity(request));
        return ResponseEntity.ok(mapper.toSystemResponse(entity));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResponseEntity<ResourcesResponse> update(@PathVariable("id") final Long id, @Valid @RequestBody final UpdateResourcesRequest request) {
        log.info("[request] update '{}' {}", id, request);
        final ResourceEntity entity = service.findById(id);
        Assert.notNull(entity, "Role not found");
        Assert.isTrue(request.version() != entity.getVersion(), "Version mismatch");

        return ResponseEntity.ok(mapper.toSystemResponse(service.update(mapper.update(request, entity))));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public ResponseEntity<ResourcesResponse> patch(@PathVariable("id") final Long id, @RequestBody final UpdateResourcesRequest request) {
        log.info("[request] patch  '{}' {}", id, request);
        final ResourceEntity entity = service.findById(id);
        Assert.notNull(entity, "Role not found");
        Assert.isTrue(request.version() != entity.getVersion(), "Version mismatch");
        return ResponseEntity.ok(this.getMapper().toSystemResponse(service.update(mapper.update(request, entity))));
    }
}
