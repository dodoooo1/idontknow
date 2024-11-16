package com.idontknow.business.application.services.system;

import com.idontknow.business.application.services.system.dto.CreateSysRoleRequest;
import com.idontknow.business.application.services.system.dto.UpdateSysRoleRequest;

/**
 * @description:
 * @title: SysRoleService
 * @package com.idontknow.business.application.services.system
 * @author: glory
 * @date: 2024/11/9 17:13
 */
public interface SysRoleService {
    void create(CreateSysRoleRequest request);

    void update(String id, UpdateSysRoleRequest request);

    void delete(Long id);
}
