package com.idontknow.business.application.services.system.dto;

public record UpdateOrganizationsRequest(Long id, String name, String description, String type, String level,
                                         String code, String mobile, String address, int version) {

}
