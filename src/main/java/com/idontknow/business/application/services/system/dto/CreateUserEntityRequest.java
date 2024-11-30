package com.idontknow.business.application.services.system.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

/**
 * @description: 新增用户
 * @title: CreateSysUserRequest
 * @package com.idontknow.business.application.services.command.cmd
 * @author: glory
 * @date: 2024/10/24 10:13
 */
public record CreateUserEntityRequest(
        @NotBlank(message = "Username cannot be empty!")
        String username,
        @NotBlank(message = "Password cannot be empty!")
        String password,
        @NotBlank(message = "Phone cannot be empty!")
        String phone,
        String nickname,
        String name,
        @NotBlank(message = "Email cannot be empty!")
        String email,
        Set<String> roleIds,
        String organizationId) {
}
