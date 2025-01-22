package com.idontknow.business.application.services.system.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @description:
 * @title: LoginResponse
 * @package com.idontknow.business.application.services.system.dto
 * @author: glory
 * @date: 2024/12/7 17:23
 */
@Getter
@Setter
@Builder
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private boolean multiOrganization;
    private Set<OrganizationsResponse> organizations;
}
