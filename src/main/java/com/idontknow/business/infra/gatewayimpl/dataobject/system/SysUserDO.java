package com.idontknow.business.infra.gatewayimpl.dataobject.system;

import com.idontknow.business.infra.gatewayimpl.dataobject.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = SysUserDO.TABLE_NAME)
public class SysUserDO extends BaseEntity {
    public static final String TABLE_NAME = "sys_user";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 64)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(length = 255)
    private String salt;

    @Column(length = 20)
    private String phone;

    @Column(length = 255)
    private String avatar;

    @Column(length = 64)
    private String nickname;

    @Column(length = 64)
    private String name;

    @Column(nullable = false, length = 128)
    private String email;

    @Column(nullable = false)
    private Long tenantId;
}
