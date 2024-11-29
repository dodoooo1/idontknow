package com.idontknow.business.infra.gatewayimpl.dataobject.system;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @description:
 * @title: SysUserRoleDO
 * @package com.idontknow.business.infra.gatewayimpl.dataobject.system
 * @author: glory
 * @date: 2024/11/17 17:40
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = SysUserRoleDO.TABLE_NAME)
public class SysUserRoleDO {
    public static final String TABLE_NAME = "sys_user_role";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "role_id")
    private Long roleId;
}
