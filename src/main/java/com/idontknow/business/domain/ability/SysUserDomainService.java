package com.idontknow.business.domain.ability;

import com.idontknow.business.domain.entities.system.SysUser;
import com.idontknow.business.domain.gateway.SysUserGateway;
import com.idontknow.business.infra.assembler.SysUserMapper;
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

    private final SysUserGateway sysUserGateway;
    private final SysUserMapper mapper;


    public void create(SysUser sysUser) {
        sysUser.setCreatedBy("admin");
        sysUser.setUpdatedBy("admin");
        sysUser.setStatus("active");

        sysUserGateway.create(sysUser);
    }
    //更新状态，启用、禁用
    public void updateStatus( SysUser sysUser, String status) {
       sysUser.updateStatus(status);
        sysUserGateway.update(sysUser);
    }
    //修改密码
    public void updatePassword( SysUser sysUser, String newPassword) {
        sysUser.updatePassword(newPassword);
        sysUserGateway.update(sysUser);
    }
    public void update(SysUser sysUser) {
        sysUserGateway.update(sysUser);
    }

    public void delete(Long[] ids) {
        sysUserGateway.delete(ids);
    }

}
