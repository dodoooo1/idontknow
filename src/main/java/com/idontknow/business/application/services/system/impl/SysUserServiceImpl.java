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

import java.util.Set;

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
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends BaseService<SysUser> implements SysUserService {
    private final SysUserMapper mapper;
    private final SysUserDomainService sysUserDomainService;


    @Override
    public void create(CreateSysUserRequest createSysUserRequest) {
        Set<String> roleIds = createSysUserRequest.roleIds();
        sysUserDomainService.create(mapper.toEntity(createSysUserRequest));
    }


    @Override
    public SysUserResponse findById(String id) {
        return mapper.PersistToResponse(sysUserDomainService.findById(id));
    }

    @Override
    public void updateStatus(UpdateSysUserRequest updateSysUserRequest) {
        sysUserDomainService.updateStatus(updateSysUserRequest);
    }

    @Override
    public SysUser loadUserByUsername(String username) {
        SysUserDO sysUserDO = sysUserDomainService.loadUserByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return mapper.PersistToEntity(sysUserDO);

    }

    @Override
    public void update(UpdateSysUserRequest updateSysUserRequest) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        Long longId = Long.valueOf(id);
        sysUserDomainService.delete(new Long[]{longId});
        //删除用户关联的角色
        sysUserDomainService.deleteUserRoleAssociation(longId);
    }

    @Override
    public boolean matchesPassword(String loginPassword, String password) {
        return sysUserDomainService.matchesPassword(loginPassword, password);
    }

    @Override
    public void updatePassword(UpdateSysUserRequest updateSysUserRequest) {
        sysUserDomainService.updatePassword(updateSysUserRequest);
    }


}
