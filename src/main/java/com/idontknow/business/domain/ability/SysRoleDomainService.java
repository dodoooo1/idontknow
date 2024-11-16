package com.idontknow.business.domain.ability;

import com.idontknow.business.domain.entities.system.SysRole;
import com.idontknow.business.domain.entities.system.SysUser;
import com.idontknow.business.domain.gateway.SysRoleGateway;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 */
@Getter
@Component
@RequiredArgsConstructor
public class SysRoleDomainService {

    private final SysRoleGateway gateway;
    //private final SysRoleMapper mapper;


    public void create(SysRole sysUser) {
        sysUser.setCreatedBy("admin");
        sysUser.setUpdatedBy("admin");

        gateway.create(sysUser);
    }
    public void update(SysRole sysRole) {
        gateway.update(sysRole);
    }

    public void delete(Long[] ids) {
        gateway.delete(ids);
    }

}
