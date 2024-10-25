package com.idontknow.business.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SysUserDTO {

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
