package com.idontknow.business.application.services.system.impl;

import com.idontknow.business.application.services.BaseService;
import com.idontknow.business.application.services.system.SysUserService;
import com.idontknow.business.application.services.system.command.cmd.UpdateSysUserRequest;
import com.idontknow.business.application.services.system.command.impl.SysUserCreateCmdExe;
import com.idontknow.business.application.services.system.query.impl.SysUserQueryExe;
import com.idontknow.business.application.services.system.query.qry.SysUserResponse;
import com.idontknow.business.domain.entities.system.SysUser;
import com.idontknow.business.infra.assembler.SysUserMapper;
import com.idontknow.business.infra.gatewayimpl.dataobject.system.SysUserDO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @title: UserService
 * @package com.idontknow.business.application.services.query
 * @author: glory
 * @date: 2024/10/23 16:00
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends BaseService<SysUser> implements SysUserService {
    private final SysUserCreateCmdExe sysUserCreateCmdExe;
    private final SysUserQueryExe sysUserQueryExe;
    private final SysUserMapper mapper;

    @Override
    public void create(SysUser sysUser) {
        sysUserCreateCmdExe.create(sysUser);
        applicationEventPublisher.publishEvent(sysUser);
    }

    @Override
    public SysUser findByUsername(String username) {
        return mapper.PersistToEntity(sysUserQueryExe.findByUsername(username));
    }

    @Override
    public SysUserResponse findById(String id) {
        return mapper.PersistToResponse(sysUserQueryExe.findById(id));
    }

    @Override
    public void updateStatus(UpdateSysUserRequest updateSysUserRequest) {
        SysUserDO byId = sysUserQueryExe.findById(updateSysUserRequest.id());
        SysUser sysUser = mapper.PersistToEntity(byId);
        sysUserCreateCmdExe.updateStatus(sysUser,updateSysUserRequest.status());
    }

}
