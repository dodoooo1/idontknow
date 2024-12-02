package com.idontknow.business.adapter.system;


import com.idontknow.business.adapter.base.BaseSystemController;
import com.idontknow.business.application.services.system.UsersService;
import com.idontknow.business.application.services.system.dto.*;
import com.idontknow.business.constants.AppUrls;
import com.idontknow.business.domain.entities.system.UserEntity;
import com.idontknow.business.infra.assembler.UserEntityMapper;
import com.idontknow.business.shared.ApiListPaginationSimple;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Slf4j
@RestController
@RequestMapping(UserEntityController.BASE_URL)
@RequiredArgsConstructor
public class UserEntityController extends BaseSystemController<
        UserEntity,
        CreateUsersRequest,
        UpdateUsersRequest,
        UserEntityResponse> {

    private final UsersService service;
    private final UserEntityMapper mapper;
    public static final String BASE_URL = AppUrls.USER;

    @GetMapping("/listByPage")
    public ApiListPaginationSimple<UserEntityResponse> listByPage(@RequestBody SearchUsersRequest request) {
        return service.listByPage(request);
    }

    /**
     * Creates a new user entity based on the provided CreateUserEntityRequest.
     *
     * @param request the request object containing the information of the user to create
     * @return the System User Response object representing the created user entity
     */
    @PostMapping
    public UserEntityResponse create(@RequestBody CreateUsersRequest request) {
        log.info("[request] create {}", request);
        UserEntity entity = mapper.toEntity(request);
        return mapper.toSystemResponse(service.create(entity));
    }

    /**
     * Deletes a user entity with the specified ID.
     *
     * @param userId the ID of the user entity to delete
     * @return a ResponseEntity indicating the success of the delete operation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String userId) {
        service.delete(userId);
        return ResponseEntity.ok().build();
    }

    /**
     * Deletes a user entity with the specified ID.
     *
     * @param userIds the ID of the user entity to delete
     * @return a ResponseEntity indicating the success of the delete operation
     */
    @DeleteMapping
    public ResponseEntity<Void> bulkDeleteUsers(@RequestBody List<Long> userIds) {
        service.bulkDeleteUsers(userIds);
        return ResponseEntity.ok().build();
    }

    /**
     * Updates the user entity with the data provided in the UpdateUserEntityRequest.
     *
     * @param updateUsersRequest the request object containing the updated user entity details
     * @return a ResponseEntity indicating the success of the update operation
     */
    @PutMapping
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateUsersRequest updateUsersRequest) {
        service.update(updateUsersRequest);
        return ResponseEntity.ok().build();
    }

    /**
     * Updates the status of a user entity based on the provided UpdateUserEntityRequest object.
     *
     * @param request the request object containing the updated status details
     * @return a ResponseEntity representing the success of the status update operation
     */
    @PatchMapping("/status")
    public ResponseEntity<Void> updateStatus(@RequestBody UpdateUsersRequest request) {
        log.info("[request] update '{}' {}", request.id(), request);
        service.updateStatus(request);
        return ResponseEntity.ok().build();
    }

    /**
     * Updates the password for a user entity.
     *
     * @param updateUsersRequest the request containing the updated password details
     * @return a ResponseEntity representing the success of the password update operation
     */
    @PatchMapping("/password")
    public ResponseEntity<Void> updatePassword(@RequestBody UpdateUsersRequest updateUsersRequest) {
        service.updatePassword(updateUsersRequest);
        return ResponseEntity.ok().build();
    }

    /**
     * Finds a user by the provided ID.
     *
     * @param id The unique identifier of the user to retrieve.
     * @return ResponseEntity<SysUserResponse> A ResponseEntity containing the SystemUserResponse for the user with the specified ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserEntityResponse> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(mapper.toSystemResponse(service.findById(id)));
    }
}
