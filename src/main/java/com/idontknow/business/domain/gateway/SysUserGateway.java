package com.idontknow.business.domain.gateway;

import com.idontknow.business.domain.entities.system.SysUser;
import com.idontknow.business.infra.gatewayimpl.dataobject.system.SysUserDO;

import java.util.Optional;

/**
 * 系统用户网关
 */
public interface SysUserGateway {

    // 检查用户名是否存在
    boolean isUsernameTaken(String username);

    // 检查邮箱是否存在
    boolean isEmailTaken(String email);

    void create(SysUser sysUser);

    void update(SysUser sysUser);

    void delete(Long[] ids);

    Optional<SysUserDO> loadUserByUsername(String username);

    SysUserDO findById(Long id);

}
