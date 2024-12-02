package com.idontknow.business.adapter.pubic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.idontknow.business.application.services.system.AuthenticationService;
import com.idontknow.business.application.services.system.dto.CreateUsersRequest;
import com.idontknow.business.application.services.system.dto.LoginRequest;
import com.idontknow.business.application.services.system.dto.UserEntityResponse;
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

    @PostMapping("/signup")
    public ResponseEntity<UserEntityResponse> register(@RequestBody CreateUsersRequest createUsersRequest) {
        authenticationService.signup(createUsersRequest);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody LoginRequest loginUserDto) throws JsonProcessingException {
        String token = authenticationService.authenticate(loginUserDto);
        return ResponseEntity.ok(token);
    }
}
