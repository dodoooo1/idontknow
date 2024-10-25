package com.idontknow.business.interfaces.system;

import com.idontknow.business.application.dto.LoginRequest;
import com.idontknow.business.application.services.system.SysUserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Getter
@RequestMapping("/auth")
public class AuthController {

    private final SysUserService sysUserService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginDto) {
        return ResponseEntity.ok(sysUserService.login(loginDto));
    }
}
