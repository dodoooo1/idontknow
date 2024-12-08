package com.idontknow.business.application.services.system.dto;

public record ResourcesResponse(Long id, String name, String url, String component, Boolean isRoute, String level,
                                String code, String status) {

}
