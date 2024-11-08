package com.idontknow.business.facades;

import com.idontknow.business.application.dto.AuthenticationUser;
import com.idontknow.business.exceptions.InternalServerErrorException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@UtilityClass
public class AuthFacade {
    public static String getUserEmail() {
        return getUserEmailOptional().orElse(StringUtils.EMPTY);
    }

    public static Optional<String> getUserEmailOptional() {
        try {

            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (Objects.isNull(authentication) || "anonymousUser".equalsIgnoreCase(authentication.getName())) {
                return Optional.empty();
            }
            final AuthenticationUser principal = (AuthenticationUser) authentication.getPrincipal();
            return Optional.ofNullable(principal.getEmail());

        } catch (final Exception ex) {
            log.error("error getting user_email from AuthFacade", ex);
            throw new InternalServerErrorException();
        }
    }

}
