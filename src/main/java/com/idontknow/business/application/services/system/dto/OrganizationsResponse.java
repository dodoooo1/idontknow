package com.idontknow.business.application.services.system.dto;

public record OrganizationsResponse(Long id, String name, String description, String type, String level, String code,
                                    String status) {

}
