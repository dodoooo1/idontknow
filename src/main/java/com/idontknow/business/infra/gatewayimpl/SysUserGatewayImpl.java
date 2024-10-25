package com.idontknow.business.infra.gatewayimpl;

import com.idontknow.business.domain.entities.system.SysUserEntity;
import com.idontknow.business.domain.gateway.SysUserGateway;
import com.idontknow.business.infra.gatewayimpl.dataobject.base.BaseGateway;
import com.idontknow.business.infra.gatewayimpl.dataobject.system.SysUserDO;
import com.idontknow.business.infra.gatewayimpl.repositories.SysUserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @title: SysUserGatewayImpl
 * @package com.idontknow.business.infra.gatewayimpl
 * @author: glory
 * @date: 2024/10/23 15:46
 */
@Getter
@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class SysUserGatewayImpl extends BaseGateway<SysUserDO> implements SysUserGateway {
private final SysUserRepository repository;
    @Override
    public boolean isUsernameTaken(String username) {
        return false;
    }

    @Override
    public boolean isEmailTaken(String email) {
        return false;
    }

    @Override
    public void create(SysUserEntity sysUserEntity) {

    }

    @Override
    public void update(SysUserEntity sysUserEntity) {

    }

    @Override
    public void delete(Long[] ids) {

    }

}
