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
public class SysDictItemEntity {

    private Long id;

    private SysDictEntity dict;

    private String itemValue;

    private String itemLabel;

    private Integer sort;

    private String createdBy;

    private String updatedBy;

    private Date createdAt;

    private Date updatedAt;

    private String remarks;

    private String status;
}
