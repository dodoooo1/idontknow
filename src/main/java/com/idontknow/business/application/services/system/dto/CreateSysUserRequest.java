package com.idontknow.business.application.services.system.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @description: 新增用户
 * @title: CreateSysUserRequest
 * @package com.idontknow.business.application.services.command.cmd
 * @author: glory
 * @date: 2024/10/24 10:13
 */
public record CreateSysUserRequest(
        @NotBlank(message = "Username cannot be empty!")
        String username,
        @NotBlank(message = "Password cannot be empty!")
        String password,
        String phone,
        String nickname,
        @NotBlank(message = "Name cannot be empty!")
        String name,
        @NotBlank(message = "Email cannot be empty!")
        String email,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Set<String> roleIds,
        String departmentId) {
}
