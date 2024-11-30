package com.idontknow.business.domain.gateway;

import com.idontknow.business.domain.entities.system.UserEntity;

import java.util.Optional;

/**
 * 系统用户网关
 */
public interface UserEntityGateway {

    // 检查用户名是否存在
    boolean isUsernameTaken(String username);

    // 检查邮箱是否存在
    boolean isEmailTaken(String email);

    void create(UserEntity sysUser);

    void update(UserEntity sysUser);

    void delete(Long[] ids);

    Optional<UserEntity> loadUserByUsername(String username);

    UserEntity findById(Long id);

}
