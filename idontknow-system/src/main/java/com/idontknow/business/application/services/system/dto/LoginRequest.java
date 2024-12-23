package com.idontknow.business.application.services.system.dto;

/**
 * @description:
 * @title: LoginRequest
 * @package com., com.idontknow.business.application.services.system.dto
 * @author: glory
 * @date: 2024/10/25 14:16
 */
public record LoginRequest(String username, String password, String email, Long organizationId) {
}

