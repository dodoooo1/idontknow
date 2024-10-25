package com.idontknow.business.facades;

import com.idontknow.business.constants.JWTClaims;
import com.idontknow.business.exceptions.InternalServerErrorException;

import java.util.Objects;
import java.util.Optional;

import io.jsonwebtoken.Jwt;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
@UtilityClass
public class AuthFacade {
  public static String getUserEmail() {
    return getUserEmailOptional().orElse(StringUtils.EMPTY);
  }

  public static Optional<String> getUserEmailOptional() {
    try {

      final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      if (Objects.nonNull(authentication)) {
        final Object principal = authentication.getPrincipal();
        return Optional.ofNullable("principal");

      }

      return Optional.empty();

    } catch (final Exception ex) {
      log.error("error getting user_email from AuthFacade", ex);
      throw new InternalServerErrorException();
    }
  }

}
