package com.idontknow.business.application.services.system;

import com.idontknow.business.application.services.system.dto.CreateSysUserRequest;
import com.idontknow.business.application.services.system.dto.SysUserResponse;
import com.idontknow.business.application.services.system.dto.UpdateSysUserRequest;

public interface SysUserService {
    void create(CreateSysUserRequest createSysUserRequest);

    SysUserResponse findById(String id);

    void updateStatus(String id,UpdateSysUserRequest updateSysUserRequest);


    SysUserResponse loadUserByUsername(String username);
}
