package com.idontknow.business.application.services.system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

/**
 * @description: 新增用户
 * @title: CreateSysUserRequest
 * @package com.idontknow.business.application.services.command.cmd
 * @author: glory
 * @date: 2024/10/24 10:13
 */

public record UpdateSysUserRequest(
        @NotBlank(message = "id不能为空")
        String id,
        @NotBlank(message = "用户名不能为空")
        String username,
        String phone,
        String password,
        String newPassword,
        String avatar,
        String nickname,
        @NotBlank(message = "姓名不能为空")
        String name,
        @Email(message = "邮件格式错误")
        String email,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        @NotBlank(message = "状态不能为空")
        String status) {

}
