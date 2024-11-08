package com.idontknow.business.application.services.system.query.impl;

import com.idontknow.business.infra.gatewayimpl.dataobject.system.QSysUserDO;
import com.idontknow.business.infra.gatewayimpl.dataobject.system.SysUserDO;
import com.idontknow.business.infra.gatewayimpl.repositories.SysUserRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @title: SysUserQueryExe
 * @package com.idontknow.business.application.services.system.query.impl
 * @author: glory
 * @date: 2024/11/8 16:37
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserQueryExe {
    private final SysUserRepository repository;

    public SysUserDO findByUsername(String username) {

        QSysUserDO qUser = QSysUserDO.sysUserDO;

        BooleanExpression predicate = qUser.username.eq(username);

        return repository.findOne(predicate).get();

    }

    public SysUserDO findById(String id) {
        return repository.findById(Long.valueOf(id)).get();
    }
}
