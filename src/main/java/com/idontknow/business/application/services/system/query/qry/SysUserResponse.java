package com.idontknow.business.application.services.system.query.qry;

import java.time.LocalDateTime;

/**
 * @description:
 * @title: SysUserResponse
 * @package com.idontknow.business.application.services.query.qry
 * @author: glory
 * @date: 2024/10/24 10:20
 */
public record SysUserResponse(
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
        String status
) {
}
