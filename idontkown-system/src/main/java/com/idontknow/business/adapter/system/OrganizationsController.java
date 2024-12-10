package com.idontknow.business.adapter.system;

import com.idontknow.business.adapter.base.BaseSystemController;
import com.idontknow.business.application.services.system.OrganizationsService;
import com.idontknow.business.application.services.system.dto.*;
import com.idontknow.business.application.services.system.query.SearchDictionariesRequest;
import com.idontknow.business.application.services.system.query.SearchOrganizationsRequest;
import com.idontknow.business.application.services.system.query.SearchUsersRequest;
import com.idontknow.business.constants.AppUrls;
import com.idontknow.business.domain.entities.system.OrganizationEntity;
import com.idontknow.business.domain.entities.system.RoleEntity;
import com.idontknow.business.domain.entities.system.UserEntity;
import com.idontknow.business.infra.assembler.OrganizationsMapper;
import com.idontknow.business.shared.ApiListPaginationSimple;
import com.idontknow.business.shared.ApiListPaginationSuccess;
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
@RequestMapping(OrganizationsController.BASE_URL)
@RequiredArgsConstructor
@Getter
public class OrganizationsController extends BaseSystemController<
        OrganizationEntity,
        CreateOrganizationsRequest,
        UpdateOrganizationsRequest,
        OrganizationsResponse> {
    public static final String BASE_URL = AppUrls.ORGANIZATION;
    private final OrganizationsService service;
    private final OrganizationsMapper mapper;

    @GetMapping
    public ApiListPaginationSuccess<OrganizationsResponse> getOrganizations(@RequestBody SearchOrganizationsRequest request) {
        return service.getOrganizations(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<OrganizationsResponse> create(@Valid @RequestBody final CreateOrganizationsRequest request) {
        log.info("[request] create {}", request);
        final OrganizationEntity entity = service.create(mapper.toEntity(request));
        return ResponseEntity.ok(mapper.toSystemResponse(entity));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResponseEntity<OrganizationsResponse> update(@PathVariable("id") final Long id, @Valid @RequestBody final UpdateOrganizationsRequest request) {
        log.info("[request] update '{}' {}", id, request);
        final OrganizationEntity entity = service.findById(id);
        Assert.notNull(entity, "Role not found");
        Assert.isTrue(request.version() != entity.getVersion(), "Version mismatch");
        return ResponseEntity.ok(mapper.toSystemResponse( service.update(mapper.update(request,entity))));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public ResponseEntity<OrganizationsResponse> patch(@PathVariable("id") final Long id, @RequestBody final UpdateOrganizationsRequest request) {
        log.info("[request] patch  '{}' {}", id, request);
        final OrganizationEntity entity = service.findById(id);
        Assert.notNull(entity, "Role not found");
        Assert.isTrue(request.version() != entity.getVersion(), "Version mismatch");
        return ResponseEntity.ok(this.getMapper().toSystemResponse( service.update(mapper.update(request,entity))));
    }
}
