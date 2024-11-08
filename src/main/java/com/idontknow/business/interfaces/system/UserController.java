package com.idontknow.business.interfaces.system;


import com.idontknow.business.application.services.system.SysUserService;
import com.idontknow.business.application.services.system.command.cmd.UpdateSysUserRequest;
import com.idontknow.business.application.services.system.query.qry.SysUserResponse;
import com.idontknow.business.constants.AppUrls;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(UserController.BASE_URL)
@RequiredArgsConstructor
@Getter
public class UserController {

    private final SysUserService sysUserService;
    public static final String BASE_URL = AppUrls.USER;
    @GetMapping("/{id}")
    public ResponseEntity<SysUserResponse> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(sysUserService.findById(id));
    }
    //更新用户状态
    @PutMapping("/update/status")
    public ResponseEntity<Void> updateStatus(@RequestBody UpdateSysUserRequest updateSysUserRequest) {
        sysUserService.updateStatus(updateSysUserRequest);
        return ResponseEntity.ok().build();
    }

}
