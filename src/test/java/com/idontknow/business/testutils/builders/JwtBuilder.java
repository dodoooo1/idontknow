package com.idontknow.business.testutils.builders;

import com.idontknow.business.constants.JWTClaims;
import com.idontknow.business.enums.UserRolesEnum;

import java.util.List;
import java.util.Map;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@UtilityClass
public class JwtBuilder {

  public static JwtAuthenticationToken jwt(final String companySlug, final UserRolesEnum role) {
    return jwt(companySlug, role.getName());
  }

  public static JwtAuthenticationToken jwt(final String companySlug, final String role) {

    final var authority = AuthorityUtils.createAuthorityList("ROLE_" + role);
    final var roleClaim =
        Map.of(
            JWTClaims.CLAIM_REALM_ACCESS,
            Map.of(JWTClaims.CLAIM_ROLES, List.of(StringUtils.isNotBlank(role) ? role : StringUtils.EMPTY)));

    final var jwt =
        Jwt.withTokenValue("token")
            .header("alg", "none")
            .claim("scope", "read")
            .claim("company_slug", companySlug)
            .claim("realms", roleClaim)
            .build();

    return new JwtAuthenticationToken(jwt, authority);
  }
}
