package com.idontknow.business.application.services.system.dto;

public record UpdateRolesRequest(

        Long id,
        String name,
        String code,
        int version,
        String description
) {


    // getters and setters
}
