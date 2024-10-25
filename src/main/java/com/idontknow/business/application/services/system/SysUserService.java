package com.idontknow.business.application.services.system;

import com.idontknow.business.application.dto.LoginRequest;
import com.idontknow.business.domain.entities.system.SysUserEntity;

public interface SysUserService {
    void create(SysUserEntity sysUserEntity);

    SysUserEntity findByUsername(String username);

    SysUserEntity getCurrentUser();

    String login(LoginRequest loginDto);
}
