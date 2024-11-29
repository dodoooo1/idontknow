package com.idontknow.business.domain.gateway;

import com.idontknow.business.domain.entities.system.SysRole;

/**
 * 系统用户网关
 */
public interface SysRoleGateway {

    // 检查用户名是否存在
    boolean isRoleNameTaken(String roleName);

    // 检查邮箱是否存在
    boolean isRoleCodeTaken(String roleCode);

    void create(SysRole sysRole);

    void update(SysRole sysRole);

    void delete(Long[] ids);
}
