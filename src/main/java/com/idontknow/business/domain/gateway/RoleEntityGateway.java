package com.idontknow.business.domain.gateway;

import com.idontknow.business.domain.entities.system.RoleEntity;

/**
 * 系统用户网关
 */
public interface RoleEntityGateway {

    // 检查用户名是否存在
    boolean isRoleNameTaken(String roleName);

    // 检查邮箱是否存在
    boolean isRoleCodeTaken(String roleCode);

    void create(RoleEntity roleEntity);

    void update(RoleEntity roleEntity);

    void delete(Long[] ids);
}
