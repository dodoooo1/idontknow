package com.idontknow.business.application.services.system.impl;

import com.idontknow.business.application.services.BaseService;
import com.idontknow.business.application.services.system.SysUserService;
import com.idontknow.business.application.services.system.dto.CreateSysUserRequest;
import com.idontknow.business.application.services.system.dto.SysUserResponse;
import com.idontknow.business.application.services.system.dto.UpdateSysUserRequest;
import com.idontknow.business.domain.ability.SysUserDomainService;
import com.idontknow.business.domain.entities.system.SysUser;
import com.idontknow.business.infra.assembler.SysUserMapper;
import com.idontknow.business.infra.gatewayimpl.dataobject.system.SysUserDO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final SysUserMapper mapper;
    private SysUserDomainService sysUserDomainService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CreateSysUserRequest createSysUserRequest) {
        sysUserDomainService.create(mapper.toEntity(createSysUserRequest));
    }


    @Override
    public SysUserResponse findById(String id) {
        return mapper.PersistToResponse(sysUserDomainService.findById(id));
    }

    @Override
    public void updateStatus(String id,UpdateSysUserRequest updateSysUserRequest) {
        SysUserDO byId = sysUserDomainService.findById(id);
        SysUser sysUser = mapper.PersistToEntity(byId);
        sysUserDomainService.updateStatus(sysUser,updateSysUserRequest.status());
    }

    @Override
    public SysUserResponse loadUserByUsername(String username) {
        return mapper.PersistToResponse(sysUserDomainService.loadUserByUsername(username));

    }

}
