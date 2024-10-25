package com.idontknow.business.infra.gatewayimpl.dataobject.system;

import com.idontknow.business.infra.gatewayimpl.dataobject.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = SysDepartmentDO.TABLE_NAME)
public class SysDepartmentDO  extends BaseEntity {
    public static final String TABLE_NAME = "sys_department";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "parent_id", nullable = false)
    private Long parentId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "description")
    private String description;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "level", nullable = false)
    private String level;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "address")
    private String address;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "tenant_id")
    private Long tenantId;

    @Column(name = "iz_leaf")
    private Boolean izLeaf;

    // getters and setters
}
