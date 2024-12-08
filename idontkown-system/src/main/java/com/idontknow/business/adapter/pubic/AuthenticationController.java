package com.idontknow.business.adapter.pubic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.idontknow.business.application.services.system.AuthenticationService;
import com.idontknow.business.application.services.system.dto.*;
import com.idontknow.business.domain.entities.system.OrganizationEntity;
import com.idontknow.business.infra.assembler.OrganizationsMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@Getter
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final OrganizationsMapper organizationsMapper;

    @PostMapping("/signup")
    public ResponseEntity<UsersResponse> register(@RequestBody CreateUsersRequest createUsersRequest) {
        authenticationService.signup(createUsersRequest);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginUserDto) {
        //如果登录时没有指定部门id,需要判断用户是否存在多个部门中，如果存在多个部门，那么需要返回给前端当前用户的所有部门，前端弹出选择框给用户选择
        //如果存在说明已经选择了当前登录的部门
        if (Objects.isNull(loginUserDto.organizationId())){
            Set<OrganizationEntity> organizationEntities = authenticationService.checkMultiOrganization(loginUserDto);
            //如果多部门则返回所有部门，提供给用户选择，否则直接返货token
            if (organizationEntities.size()>1) {
                Set<OrganizationsResponse> organizationsResponses = new HashSet<>(organizationsMapper.toSystemResponse(organizationEntities));
               return ResponseEntity.ok(LoginResponse.builder().multiOrganization(true).organizations(organizationsResponses).build());
            }
        }
        String token = authenticationService.authenticate(loginUserDto);
        LoginResponse loginResponse = LoginResponse.builder().token(token).build();
        return ResponseEntity.ok(loginResponse);
    }
}
