package com.idontknow.business.application.services.system.dto;

public record CreateOrganizationsRequest(
        Long parentId,
        String name,
        String description,
        String type,
        String level,
        String code,
        String mobile,
        String address,
        String status
) {


    // getters and setters
}
