package com.idontknow.business.application.services.system.impl;

import com.idontknow.business.application.services.system.RoleEntityService;
import com.idontknow.business.application.services.system.dto.CreateEntityRoleRequest;
import com.idontknow.business.application.services.system.dto.UpdateRoleEntityRequest;
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
public class RoleEntityServiceImpl implements RoleEntityService {
    @Override
    public void create(CreateEntityRoleRequest request) {

    }

    @Override
    public void update(String id, UpdateRoleEntityRequest request) {

    }

    @Override
    public void delete(Long id) {

    }
}
