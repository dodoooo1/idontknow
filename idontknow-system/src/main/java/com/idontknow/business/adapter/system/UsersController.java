package com.idontknow.business.adapter.system;


import com.idontknow.business.adapter.base.BaseSystemController;
import com.idontknow.business.application.services.system.UsersService;
import com.idontknow.business.application.services.system.dto.CreateUsersRequest;
import com.idontknow.business.application.services.system.dto.UpdateUsersRequest;
import com.idontknow.business.application.services.system.dto.UsersResponse;
import com.idontknow.business.application.services.system.query.SearchUsersRequest;
import com.idontknow.business.core.constants.AppUrls;
import com.idontknow.business.domain.entities.system.UserEntity;
import com.idontknow.business.infra.assembler.UsersMapper;
import com.idontknow.business.jpa.ApiListPaginationSuccess;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@Getter
@Slf4j
@RestController
@RequestMapping(UsersController.BASE_URL)
@RequiredArgsConstructor
public class UsersController extends BaseSystemController<
        UserEntity,
        CreateUsersRequest,
        UpdateUsersRequest,
        UsersResponse> {

    private final UsersService service;
    private final UsersMapper mapper;
    public static final String BASE_URL = AppUrls.USER;

    @GetMapping
    public ApiListPaginationSuccess<UsersResponse> getUsers(@RequestBody SearchUsersRequest request) {
        return service.getUsers(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<UsersResponse> create(@Valid @RequestBody final CreateUsersRequest request) {
        log.info("[request] create {}", request);
        final UserEntity entity = service.create(mapper.toEntity(request));
        return ResponseEntity.ok(mapper.toSystemResponse(entity));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResponseEntity<UsersResponse> update(@PathVariable("id") final Long id, @Valid @RequestBody final UpdateUsersRequest request) {
        log.info("[request] update '{}' {}", id, request);
        final UserEntity entity = service.findById(id);
        Assert.notNull(entity, "Role not found");
        Assert.isTrue(request.version() != entity.getVersion(), "Version mismatch");

        return ResponseEntity.ok(mapper.toSystemResponse(service.update(mapper.update(request, entity))));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public ResponseEntity<UsersResponse> patch(@PathVariable("id") final Long id, @RequestBody final UpdateUsersRequest request) {
        log.info("[request] patch  '{}' {}", id, request);
        final UserEntity entity = service.findById(id);
        Assert.notNull(entity, "Role not found");
        Assert.isTrue(request.version() != entity.getVersion(), "Version mismatch");
        service.update(mapper.update(request, entity));
        return ResponseEntity.ok(this.getMapper().toSystemResponse(service.update(mapper.update(request, entity))));
    }
}
