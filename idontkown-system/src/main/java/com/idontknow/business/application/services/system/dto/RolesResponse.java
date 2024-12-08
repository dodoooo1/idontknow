package com.idontknow.business.application.services.system.dto;

public record RolesResponse(
        Long id,
        String name,
        String code,
        String description,
        String status) {
}
