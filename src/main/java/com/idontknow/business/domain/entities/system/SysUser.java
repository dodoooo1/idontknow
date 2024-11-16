package com.idontknow.business.domain.entities.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SysUser {
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
    private Set<SysRole> roles = new HashSet<>();

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
    public void updateStatus( String status) {
        this.status = status;

    }
    public void addRole(SysRole role) {
        if (!roles.contains(role)) {
            roles.add(role);
        }
    }

    public void removeRole(SysRole role) {
        roles.remove(role);
    }
}
