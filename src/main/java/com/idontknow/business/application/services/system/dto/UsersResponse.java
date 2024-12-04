package com.idontknow.business.application.services.system.dto;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @description:
 * @title: SysUserResponse
 * @package com.idontknow.business.application.services.query.qry
 * @author: glory
 * @date: 2024/10/24 10:20
 */
public record UsersResponse(
        Long id,
        String username,
        String phone,
        String avatar,
        String nickname,
        String name,
        String email,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String status,
        Set<RolesResponse> roles
) {
}
