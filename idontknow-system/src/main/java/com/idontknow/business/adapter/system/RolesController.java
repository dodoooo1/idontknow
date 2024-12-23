package com.idontknow.business.adapter.system;

import com.idontknow.business.adapter.base.BaseSystemController;
import com.idontknow.business.application.services.system.RolesService;
import com.idontknow.business.application.services.system.dto.CreateRolesRequest;
import com.idontknow.business.application.services.system.dto.RolesResponse;
import com.idontknow.business.application.services.system.dto.UpdateRolesRequest;
import com.idontknow.business.application.services.system.query.SearchRolesRequest;
import com.idontknow.business.core.constants.AppUrls;
import com.idontknow.business.domain.entities.system.RoleEntity;
import com.idontknow.business.infra.assembler.RolesMapper;
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
@RequestMapping(RolesController.BASE_URL)
@RequiredArgsConstructor
@Getter
public class RolesController extends BaseSystemController<
        RoleEntity,
        CreateRolesRequest,
        UpdateRolesRequest,
        RolesResponse> {
    public static final String BASE_URL = AppUrls.ROLE;
    private final RolesService service;
    private final RolesMapper mapper;

    @GetMapping
    public ApiListPaginationSuccess<RolesResponse> getRoles(@RequestBody final SearchRolesRequest request) {
        return service.getRoles(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<RolesResponse> create(@Valid @RequestBody final CreateRolesRequest request) {
        log.info("[request] create {}", request);
        final RoleEntity entity = service.create(mapper.toEntity(request));
        return ResponseEntity.ok(mapper.toSystemResponse(entity));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResponseEntity<RolesResponse> update(@PathVariable("id") final Long id, @Valid @RequestBody final UpdateRolesRequest request) {
        log.info("[request] update '{}' {}", id, request);
        final RoleEntity entity = service.findById(id);
        Assert.notNull(entity, "Role not found");
        //Assert.isTrue(request.version() == entity.getVersion(), "Version mismatch");
        return ResponseEntity.ok(mapper.toSystemResponse(service.update(mapper.update(request, entity))));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public ResponseEntity<RolesResponse> patch(@PathVariable("id") final Long id, @RequestBody final UpdateRolesRequest request) {
        log.info("[request] patch  '{}' {}", id, request);
        final RoleEntity entity = service.findById(id);
        Assert.notNull(entity, "Role not found");
        Assert.isTrue(request.version() != entity.getVersion(), "Version mismatch");
        return ResponseEntity.ok(this.getMapper().toSystemResponse(service.update(mapper.update(request, entity))));
    }
}
