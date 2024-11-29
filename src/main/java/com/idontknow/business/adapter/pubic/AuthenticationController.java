package com.idontknow.business.adapter.pubic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idontknow.business.application.services.system.AuthenticationService;
import com.idontknow.business.application.services.system.dto.CreateSysUserRequest;
import com.idontknow.business.application.services.system.dto.LoginRequest;
import com.idontknow.business.application.services.system.dto.SysUserResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Getter
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final ObjectMapper objectMapper;

    @PostMapping("/signup")
    public ResponseEntity<SysUserResponse> register(@RequestBody CreateSysUserRequest createSysUserRequest) {
        authenticationService.signup(createSysUserRequest);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody LoginRequest loginUserDto) throws JsonProcessingException {
        String token = authenticationService.authenticate(loginUserDto);
        return ResponseEntity.ok(token);
    }
}
