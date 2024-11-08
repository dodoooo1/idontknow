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
public class SysDepartmentEntity {

    private Long id;

    private Long parentId;

    private String name;

    private Integer sort;

    private String description;

    private String type;

    private String level;

    private String code;

    private String mobile;

    private String address;

    private String createdBy;

    private String updatedBy;

    private Date createdAt;

    private Date updatedAt;

    private String remarks;

    private String status;


    private Boolean izLeaf;

}
