package com.idontknow.business.domain.entities.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SysLogEntity {

    private Long id;

    private String moduleName;

    private Integer businessType;

    private String method;

    private String requestMethod;

    private String operatorType;

    private String operUrl;

    private String requestParam;

    private String response;

    private String createdBy;

    private String updatedBy;

    private Date createdAt;

    private Date updatedAt;

    private Long costTime;

}
