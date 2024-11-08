package com.idontknow.business.interfaces.pubic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idontknow.business.application.services.system.AuthenticationService;
import com.idontknow.business.application.services.system.command.cmd.CreateSysUserRequest;
import com.idontknow.business.application.services.system.command.cmd.UpdateSysUserRequest;
import com.idontknow.business.application.services.system.query.qry.SysUserQuery;
import com.idontknow.business.application.services.system.query.qry.SysUserResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Getter
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController  {
    private final AuthenticationService authenticationService;
    private final ObjectMapper objectMapper;

    @PostMapping("/signup")
    public ResponseEntity<SysUserResponse> register(@RequestBody CreateSysUserRequest createSysUserRequest) {
        authenticationService.signup(createSysUserRequest);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody SysUserQuery loginUserDto) {
        String token = authenticationService.authenticate(loginUserDto);
        return ResponseEntity.ok(token);
    }
    //获取当前用户
    @PostMapping("/current")
    public ResponseEntity<SysUserResponse> current() {
        return ResponseEntity.ok(authenticationService.getCurrentUser());
    }
    //更新用户密码
    @PutMapping("/update/password")
    public ResponseEntity<Void> updatePassword(@RequestBody UpdateSysUserRequest updateSysUserRequest) {
        authenticationService.updatePassword(updateSysUserRequest);
        return ResponseEntity.ok().build();
    }
}
