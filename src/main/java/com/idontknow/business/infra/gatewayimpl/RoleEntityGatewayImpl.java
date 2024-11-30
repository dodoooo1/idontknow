package com.idontknow.business.infra.gatewayimpl;

import com.idontknow.business.domain.entities.system.RoleEntity;
import com.idontknow.business.domain.gateway.RoleEntityGateway;
import com.idontknow.business.infra.gatewayimpl.repositories.RoleEntityRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
public class RoleEntityGatewayImpl implements RoleEntityGateway {
    private final RoleEntityRepository repository;
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
    public void create(RoleEntity roleEntity) {

    }

    @Override
    public void update(RoleEntity roleEntity) {

    }

    @Override
    public void delete(Long[] ids) {

    }

}
