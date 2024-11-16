package com.idontknow.business.domain.ability;

import com.idontknow.business.application.services.system.dto.SysUserResponse;
import com.idontknow.business.domain.entities.system.SysUser;
import com.idontknow.business.domain.gateway.SysUserGateway;
import com.idontknow.business.infra.assembler.SysUserMapper;
import com.idontknow.business.infra.gatewayimpl.dataobject.system.SysUserDO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 */
@Getter
@Component
@RequiredArgsConstructor
public class SysUserDomainService {

    private final SysUserGateway gateway;
    private final SysUserMapper mapper;


    public void create(SysUser sysUser) {
        sysUser.setCreatedBy("admin");
        sysUser.setUpdatedBy("admin");
        sysUser.setStatus("active");

        gateway.create(sysUser);
    }
    //更新状态，启用、禁用
    public void updateStatus( SysUser sysUser, String status) {
       sysUser.updateStatus(status);
        gateway.update(sysUser);
    }
    //修改密码
    public void updatePassword( SysUser sysUser,String newPassword) {
        sysUser.updatePassword(newPassword);
        gateway.update(sysUser);
    }
    public void update(SysUser sysUser) {
        gateway.update(sysUser);
    }

    public void delete(Long[] ids) {
        gateway.delete(ids);
    }

    public SysUserDO loadUserByUsername(String username) {
        return gateway.loadUserByUsername(username);
    }

    public SysUserDO findById(String id) {
        return gateway.findById(Long.valueOf(id));
    }
}
