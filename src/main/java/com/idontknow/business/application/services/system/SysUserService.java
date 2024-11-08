package com.idontknow.business.application.services.system;

import com.idontknow.business.application.services.system.command.cmd.UpdateSysUserRequest;
import com.idontknow.business.application.services.system.query.qry.SysUserResponse;
import com.idontknow.business.domain.entities.system.SysUser;

public interface SysUserService {
    void create(SysUser sysUser);

    SysUser findByUsername(String username);

    SysUserResponse findById(String id);

    void updateStatus(UpdateSysUserRequest updateSysUserRequest);
}
