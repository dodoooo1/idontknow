package com.idontknow.business.infra.gatewayimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idontknow.business.domain.entities.system.SysRole;
import com.idontknow.business.domain.entities.system.SysUser;
import com.idontknow.business.domain.gateway.SysRoleGateway;
import com.idontknow.business.domain.gateway.SysUserGateway;
import com.idontknow.business.infra.assembler.SysUserMapper;
import com.idontknow.business.infra.gatewayimpl.dataobject.base.BaseGateway;
import com.idontknow.business.infra.gatewayimpl.dataobject.system.QSysUserDO;
import com.idontknow.business.infra.gatewayimpl.dataobject.system.SysRoleDO;
import com.idontknow.business.infra.gatewayimpl.dataobject.system.SysUserDO;
import com.idontknow.business.infra.gatewayimpl.repositories.SysRoleRepository;
import com.idontknow.business.infra.gatewayimpl.repositories.SysUserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @title: SysUserGatewayImpl
 * @package com.idontknow.business.infra.gatewayimpl
 * @author: glory
 * @date: 2024/10/23 15:46
 */
@Getter
@Slf4j
@Service
@RequiredArgsConstructor
public class SysRoleGatewayImpl extends BaseGateway<SysRoleDO> implements SysRoleGateway {
    private final SysRoleRepository repository;
    private final JPAQueryFactory jpaQueryFactory;
    //private final SysRoleMapper mapper;
/*
    public void create(SysRole sysRole) {
        SysUserDO sysUserDO = mapper.toPersist(sysRole);
        try {
            log.info("save user: {}", new ObjectMapper().writeValueAsString(sysUserDO));
            log.info("sysUserEntity user: {}", new ObjectMapper().writeValueAsString(sysRole));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        repository.save(sysUserDO);

    }*/

   /* @Override
    public void update(SysRole sysRole) {
        SysUserDO sysUserDO = mapper.toPersist(sysUser);
        repository.save(sysUserDO);
    }*/

    @Override
    public boolean isRoleNameTaken(String roleName) {
        return false;
    }

    @Override
    public boolean isRoleCodeTaken(String roleCode) {
        return false;
    }

    @Override
    public void create(SysRole sysRole) {

    }

    @Override
    public void update(SysRole sysRole) {

    }

    @Override
    public void delete(Long[] ids) {

    }

}
