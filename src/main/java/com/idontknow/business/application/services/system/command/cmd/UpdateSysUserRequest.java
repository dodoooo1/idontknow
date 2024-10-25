package com.idontknow.business.application.services.system.command.cmd;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @description: 新增用户
 * @title: CreateSysUserRequest
 * @package com.idontknow.business.application.services.command.cmd
 * @author: glory
 * @date: 2024/10/24 10:13
 */

public class UpdateSysUserRequest {
    private Long id;
    private String username;
    private String password;
    private String salt;
    private String phone;
    private String avatar;
    private String nickname;
    private String name;
    private String email;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
    private Long tenantId;
}
