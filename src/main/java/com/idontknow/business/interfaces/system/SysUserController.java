package com.idontknow.business.interfaces.system;


import com.idontknow.business.application.services.system.SysUserService;
import com.idontknow.business.application.services.system.dto.CreateSysUserRequest;
import com.idontknow.business.application.services.system.dto.SysUserResponse;
import com.idontknow.business.application.services.system.dto.UpdateSysUserRequest;
import com.idontknow.business.constants.AppUrls;
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
     * @param updateSysUserRequest
     * @return
     */
    @PutMapping("/update/status/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable String id, @RequestBody UpdateSysUserRequest updateSysUserRequest) {
        service.updateStatus(id,updateSysUserRequest);
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
}
