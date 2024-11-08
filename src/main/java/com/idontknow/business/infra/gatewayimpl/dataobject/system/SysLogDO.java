package com.idontknow.business.infra.gatewayimpl.dataobject.system;

import com.idontknow.business.infra.gatewayimpl.dataobject.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = SysResourceDO.TABLE_NAME)
public class SysLogDO extends BaseEntity {
    public static final String TABLE_NAME = "sys_log";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "module_name", nullable = false)
    private String moduleName;

    @Column(name = "business_type", nullable = false)
    private Integer businessType;

    @Column(name = "method", nullable = false)
    private String method;

    @Column(name = "request_method", nullable = false)
    private String requestMethod;

    @Column(name = "operator_type")
    private String operatorType;

    @Column(name = "oper_url")
    private String operUrl;

    @Column(name = "request_param")
    private String requestParam;

    @Column(name = "response")
    private String response;

    @Column(name = "cost_time")
    private Long costTime;

    // getters and setters
}
