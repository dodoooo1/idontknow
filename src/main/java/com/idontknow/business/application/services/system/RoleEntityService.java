package com.idontknow.business.application.services.system;

import com.idontknow.business.application.services.system.dto.CreateEntityRoleRequest;
import com.idontknow.business.application.services.system.dto.UpdateRoleEntityRequest;

/**
 * @description:
 * @title: SysRoleService
 * @package com.idontknow.business.application.services.system
 * @author: glory
 * @date: 2024/11/9 17:13
 */
public interface RoleEntityService {
    void create(CreateEntityRoleRequest request);

    void update(String id, UpdateRoleEntityRequest request);

    void delete(Long id);
}
