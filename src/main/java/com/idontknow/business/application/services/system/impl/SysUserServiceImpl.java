package com.idontknow.business.application.services.system.impl;

import com.idontknow.business.application.dto.LoginRequest;
import com.idontknow.business.application.services.BaseService;
import com.idontknow.business.application.services.system.SysUserService;
import com.idontknow.business.application.services.system.command.impl.SysUserCreateCmdExe;
import com.idontknow.business.domain.entities.system.SysUserEntity;
import com.idontknow.business.infra.configs.security.auth.providers.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @title: UserService
 * @package com.idontknow.business.application.services.query
 * @author: glory
 * @date: 2024/10/23 16:00
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends BaseService<SysUserEntity> implements SysUserService {
    private final SysUserCreateCmdExe sysUserCreateCmdExe;
    private final JwtTokenService jwtTokenService;
    private final AuthenticationProvider authenticationProvider;
    @Override
    public void create(SysUserEntity sysUserEntity) {
        sysUserCreateCmdExe.create(sysUserEntity);
       applicationEventPublisher.publishEvent(sysUserEntity);
    }

    @Override
    public SysUserEntity findByUsername(String username) {
        return null;
    }

    public String login(LoginRequest loginRequest) {
        final Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final SysUserEntity user = findByUsername(loginRequest.username());
        return jwtTokenService.generateToken(user.getUsername(), user.getRoles());
    }


    public SysUserEntity getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findByUsername(userDetails.getUsername());
    }
}
