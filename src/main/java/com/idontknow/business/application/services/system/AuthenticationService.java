package com.idontknow.business.application.services.system;

import com.idontknow.business.application.services.system.dto.CreateSysUserRequest;
import com.idontknow.business.application.services.system.dto.SysUserQuery;
import com.idontknow.business.application.services.system.dto.SysUserResponse;
import com.idontknow.business.application.services.system.dto.UpdateSysUserRequest;

/**
 * @description:
 * @title: AuthenticationService
 * @package com.idontknow.business.application.services.system
 * @author: glory
 * @date: 2024/10/25 09:48
 */
public interface AuthenticationService {
    SysUserResponse findByUsername(String username);

    SysUserResponse getCurrentUser();

    String authenticate(SysUserQuery loginDto);

    void signup(CreateSysUserRequest createSysUserRequest);

    void updatePassword(UpdateSysUserRequest updateSysUserRequest);
}
