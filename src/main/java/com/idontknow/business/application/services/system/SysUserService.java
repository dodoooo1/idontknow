package com.idontknow.business.application.services.system;

import com.idontknow.business.application.services.system.dto.CreateSysUserRequest;
import com.idontknow.business.application.services.system.dto.SysUserResponse;
import com.idontknow.business.application.services.system.dto.UpdateSysUserRequest;
import com.idontknow.business.domain.entities.system.SysUser;

/**
 * Interface for managing system users.
 */
public interface SysUserService {
    /**
     * Creates a new system user based on the provided request.
     *
     * @param createSysUserRequest the request containing the information of the user to create
     */
    void create(CreateSysUserRequest createSysUserRequest);

    SysUserResponse findById(String id);

    /**
     * @param updateSysUserRequest
     */
    void updateStatus(UpdateSysUserRequest updateSysUserRequest);


    SysUser loadUserByUsername(String username);

    void update(UpdateSysUserRequest updateSysUserRequest);

    void delete(String id);

    boolean matchesPassword(String loginPassword, String password);

    void updatePassword(UpdateSysUserRequest updateSysUserRequest);
}
