package com.idontknow.business.application.services.system.command.impl;

import com.idontknow.business.domain.ability.SysUserDomainService;
import com.idontknow.business.domain.entities.system.SysUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @title: SysUserAddCmdExe
 * @package com.idontknow.business.application.services.system.command.impl
 * @author: glory
 * @date: 2024/10/24 11:13
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserCreateCmdExe {
    private final SysUserDomainService sysUserDomainService;

    public void create(SysUser sysUser) {
        sysUserDomainService.create(sysUser);
    }

    public void updateStatus(SysUser sysUser,String status) {
        sysUserDomainService.updateStatus(sysUser, status);
    }

    public void updatePassword(SysUser sysUser, String password, String newPassword) {
        sysUserDomainService.updatePassword(sysUser, newPassword);
    }
}
