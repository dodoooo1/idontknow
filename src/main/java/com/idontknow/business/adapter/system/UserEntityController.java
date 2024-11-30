package com.idontknow.business.adapter.system;


import com.idontknow.business.adapter.base.BaseSystemController;
import com.idontknow.business.application.services.system.UserEntityService;
import com.idontknow.business.application.services.system.dto.CreateUserEntityRequest;
import com.idontknow.business.application.services.system.dto.SysUserResponse;
import com.idontknow.business.application.services.system.dto.UpdateUserEntityRequest;
import com.idontknow.business.constants.AppUrls;
import com.idontknow.business.domain.entities.system.UserEntity;
import com.idontknow.business.infra.assembler.UserEntityMapper;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Getter
@Slf4j
@RestController
@RequestMapping(UserEntityController.BASE_URL)
@RequiredArgsConstructor
public class UserEntityController extends BaseSystemController<
        UserEntity,
        CreateUserEntityRequest,
        UpdateUserEntityRequest,
        SysUserResponse> {

    private final UserEntityService service;
    private final UserEntityMapper mapper;
    public static final String BASE_URL = AppUrls.USER;

    @GetMapping("/{id}")
    public ResponseEntity<SysUserResponse> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(mapper.toSystemResponse(service.findById(id)));
    }

    /**
     * 更新用户状态
     *
     * @param updateUserEntityRequest
     * @return
     */
    @PutMapping("/update/status")
    public ResponseEntity<Void> updateStatus(@RequestBody UpdateUserEntityRequest updateUserEntityRequest) {
        service.updateStatus(updateUserEntityRequest);
        return ResponseEntity.ok().build();
    }

    /**
     * 新增用户
     */
    @PostMapping
    public SysUserResponse create(@RequestBody CreateUserEntityRequest createUserEntityRequest) {
        UserEntity entity = mapper.toEntity(createUserEntityRequest);
        return mapper.toSystemResponse(service.create(entity));
    }

    //删除用户
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * @param updateUserEntityRequest
     * @return
     */

    @PutMapping()
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateUserEntityRequest updateUserEntityRequest) {
        service.update(updateUserEntityRequest);
        return ResponseEntity.ok().build();
    }

    //更新用户密码
    @PutMapping("/update/password")
    public ResponseEntity<Void> updatePassword(@RequestBody UpdateUserEntityRequest updateUserEntityRequest) {
        service.updatePassword(updateUserEntityRequest);
        return ResponseEntity.ok().build();
    }
}
