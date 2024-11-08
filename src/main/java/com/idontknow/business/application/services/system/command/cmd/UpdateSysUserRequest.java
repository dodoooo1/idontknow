package com.idontknow.business.application.services.system.command.cmd;

import java.time.LocalDateTime;

/**
 * @description: 新增用户
 * @title: CreateSysUserRequest
 * @package com.idontknow.business.application.services.command.cmd
 * @author: glory
 * @date: 2024/10/24 10:13
 */

public record UpdateSysUserRequest ( String id,
         String username,
         String password,
         String salt,
         String newPassword,
         String phone,
         String avatar,
         String nickname,
         String name,
         String email,
         String createdBy,
         String updatedBy,
         LocalDateTime createdAt,
         LocalDateTime updatedAt,
         String status){

}
