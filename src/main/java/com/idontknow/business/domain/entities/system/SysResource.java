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
public class SysResource {

    private Long id;

    private Long parentId;

    private String name;

    private String url;

    private String component;

    private Boolean isRoute;

    private String componentName;

    private String redirect;

    private String level;

    private String code;

    private Integer sort;

    private String icon;

    private Boolean isLeaf;

    private Boolean keepAlive;

    private Boolean hidden;

    private Boolean hideTab;

    private String description;

    private String createdBy;

    private String updatedBy;

    private Date createdAt;

    private Date updatedAt;

    private String remarks;

    private String status;

    private String openType;


}
