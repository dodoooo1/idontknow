package com.idontknow.business.application.services.system.query.impl;

import com.idontknow.business.application.dto.AuthenticationUser;
import com.idontknow.business.application.services.BaseService;
import com.idontknow.business.application.services.system.AuthenticationService;
import com.idontknow.business.application.services.system.command.cmd.CreateSysUserRequest;
import com.idontknow.business.application.services.system.command.cmd.UpdateSysUserRequest;
import com.idontknow.business.application.services.system.command.impl.SysUserCreateCmdExe;
import com.idontknow.business.application.services.system.query.qry.SysUserQuery;
import com.idontknow.business.application.services.system.query.qry.SysUserResponse;
import com.idontknow.business.domain.entities.system.SysUser;
import com.idontknow.business.infra.assembler.SysUserMapper;
import com.idontknow.business.infra.configs.security.auth.providers.JwtTokenService;
import com.idontknow.business.infra.gatewayimpl.dataobject.system.SysUserDO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @title: AuthenticationServiceImpl
 * @package com.idontknow.business.application.services.system.query.impl
 * @author: glory
 * @date: 2024/10/25 09:48
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl extends BaseService<SysUser> implements AuthenticationService {
    private final JwtTokenService jwtTokenService;
    private final AuthenticationProvider authenticationProvider;
    private final SysUserQueryExe sysUserQueryExe;
    private final SysUserCreateCmdExe sysUserCreateCmdExe;
    private final SysUserMapper mapper ;
     private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public SysUserResponse findByUsername(String username) {
        return mapper.PersistToResponse(sysUserQueryExe.findByUsername(username));
    }

    public String authenticate(SysUserQuery loginRequest) {
        final Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthenticationUser userDetails = (AuthenticationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwtTokenService.generateToken(userDetails);
    }

    @Override
    public void signup(CreateSysUserRequest createSysUserRequest) {
        SysUser entity = mapper.toEntity(createSysUserRequest);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        sysUserCreateCmdExe.create(entity);
    }

    @Override
    public void updatePassword(UpdateSysUserRequest updateSysUserRequest) {
        SysUserDO byId = sysUserQueryExe.findById(updateSysUserRequest.id());
        SysUser sysUser = mapper.PersistToEntity(byId);
        String password = passwordEncoder.encode(updateSysUserRequest.password());
        String newPassword = passwordEncoder.encode(updateSysUserRequest.newPassword());
        sysUserCreateCmdExe.updatePassword(sysUser,password,newPassword);
    }


    public SysUserResponse getCurrentUser() {
        AuthenticationUser userDetails = (AuthenticationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findByUsername(userDetails.getUsername());
    }

}
