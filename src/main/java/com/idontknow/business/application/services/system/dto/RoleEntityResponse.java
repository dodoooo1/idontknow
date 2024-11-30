package com.idontknow.business.application.services.system.dto;

public record RoleEntityResponse(
        Long id,
        String name,
        String code,
        String description,
        String status) {
    // getters and setters
}
