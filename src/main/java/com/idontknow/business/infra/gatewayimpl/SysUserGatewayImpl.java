package com.idontknow.business.infra.gatewayimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idontknow.business.domain.entities.system.SysUser;
import com.idontknow.business.domain.gateway.SysUserGateway;
import com.idontknow.business.infra.assembler.SysUserMapper;
import com.idontknow.business.infra.gatewayimpl.dataobject.base.BaseGateway;
import com.idontknow.business.infra.gatewayimpl.dataobject.system.QSysUserDO;
import com.idontknow.business.infra.gatewayimpl.dataobject.system.SysUserDO;
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
public class SysUserGatewayImpl extends BaseGateway<SysUserDO> implements SysUserGateway {
    private final SysUserRepository repository;
    private final JPAQueryFactory jpaQueryFactory;
    private final SysUserMapper mapper;
    @Override
    public boolean isUsernameTaken(String username) {
        return false;
    }

    @Override
    public boolean isEmailTaken(String email) {
        QSysUserDO qSysUserDO = QSysUserDO.sysUserDO;
        List<Long> list = jpaQueryFactory.select(qSysUserDO.id).from(qSysUserDO).where(qSysUserDO.email.eq(email)).fetch();
        return list.isEmpty();
    }

    public void create(SysUser sysUser) {
        SysUserDO sysUserDO = mapper.toPersist(sysUser);
        try {
            log.info("save user: {}", new ObjectMapper().writeValueAsString(sysUserDO));
            log.info("sysUserEntity user: {}", new ObjectMapper().writeValueAsString(sysUser));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        repository.save(sysUserDO);

    }

    @Override
    public void update(SysUser sysUser) {
        SysUserDO sysUserDO = mapper.toPersist(sysUser);
        repository.save(sysUserDO);
    }

    @Override
    public void delete(Long[] ids) {

    }

}
