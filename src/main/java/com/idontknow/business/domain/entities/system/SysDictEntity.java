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
public class SysDictEntity {

    private Long id;

    private String dictType;

    private String description;

    private String createdBy;

    private String updatedBy;
    private Date createdAt;

    private Date updatedAt;

    private String remarks;

    private String status;

}
