package com.idontknow.business.application.services.system.impl;

import com.idontknow.business.application.services.BaseService;
import com.idontknow.business.application.services.system.SysRoleService;
import com.idontknow.business.application.services.system.dto.CreateSysRoleRequest;
import com.idontknow.business.application.services.system.dto.UpdateSysRoleRequest;
import com.idontknow.business.domain.entities.system.SysRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @title: SysRoleServiceImpl
 * @package com.idontknow.business.application.services.system.impl
 * @author: glory
 * @date: 2024/11/9 17:13
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends BaseService<SysRole> implements SysRoleService {
    @Override
    public void create(CreateSysRoleRequest request) {

    }

    @Override
    public void update(String id, UpdateSysRoleRequest request) {

    }

    @Override
    public void delete(Long id) {

    }
}
