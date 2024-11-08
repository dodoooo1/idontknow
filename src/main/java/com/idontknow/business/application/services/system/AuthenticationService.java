package com.idontknow.business.application.services.system;

import com.idontknow.business.application.services.system.command.cmd.CreateSysUserRequest;
import com.idontknow.business.application.services.system.command.cmd.UpdateSysUserRequest;
import com.idontknow.business.application.services.system.query.qry.SysUserQuery;
import com.idontknow.business.application.services.system.query.qry.SysUserResponse;

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
