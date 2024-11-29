package com.idontknow.business.adapter.system;


import com.idontknow.business.application.services.system.SysUserService;
import com.idontknow.business.application.services.system.dto.CreateSysUserRequest;
import com.idontknow.business.application.services.system.dto.SysUserResponse;
import com.idontknow.business.application.services.system.dto.UpdateSysUserRequest;
import com.idontknow.business.constants.AppUrls;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(SysUserController.BASE_URL)
@RequiredArgsConstructor
@Getter
public class SysUserController {

    private final SysUserService service;
    public static final String BASE_URL = AppUrls.USER;

    @GetMapping("/{id}")
    public ResponseEntity<SysUserResponse> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    /**
     * 更新用户状态
     *
     * @param updateSysUserRequest
     * @return
     */
    @PutMapping("/update/status")
    public ResponseEntity<Void> updateStatus(@RequestBody UpdateSysUserRequest updateSysUserRequest) {
        service.updateStatus(updateSysUserRequest);
        return ResponseEntity.ok().build();
    }

    /**
     * 新增用户
     */
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateSysUserRequest createSysUserRequest) {
        service.create(createSysUserRequest);
        return ResponseEntity.ok().build();
    }

    //删除用户
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * @param updateSysUserRequest
     * @return
     */

    @PutMapping()
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateSysUserRequest updateSysUserRequest) {
        service.update(updateSysUserRequest);
        return ResponseEntity.ok().build();
    }

    //更新用户密码
    @PutMapping("/update/password")
    public ResponseEntity<Void> updatePassword(@RequestBody UpdateSysUserRequest updateSysUserRequest) {
        service.updatePassword(updateSysUserRequest);
        return ResponseEntity.ok().build();
    }
}
