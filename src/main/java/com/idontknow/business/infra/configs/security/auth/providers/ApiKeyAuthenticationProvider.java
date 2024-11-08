package com.idontknow.business.infra.configs.security.auth.providers;


import com.idontknow.business.application.dto.AuthenticationUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {

    private static final String LOG_NAME = "ApiKeyAuthProvider";
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public ApiKeyAuthenticationProvider(final UserDetailsService userDetailsService, final BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Authentication authenticate(final Authentication authentication)
            throws AuthenticationException {

        final String apiKeyInRequest = (String) authentication.getPrincipal();

        if (StringUtils.isBlank(apiKeyInRequest)) {
            log.info("[{}] api-key is not defined on request, returning 401", LOG_NAME);
            throw new InsufficientAuthenticationException("api-key is not defined on request");
        } else {
            log.debug("[{}] start searching for api-key '{}'", LOG_NAME, apiKeyInRequest);
            //直接传token，需要执行邮件和用户名等
            final AuthenticationUser userDetails = (AuthenticationUser) this.userDetailsService.loadUserByUsername(apiKeyInRequest);
            if (userDetails != null) {
                return new ApiKeyAuthentication(
                        apiKeyInRequest, true, userDetails, userDetails.getSimpleGrantedAuthority());
            }
            log.info("[{}] api-key '{}' not found, returning 401", LOG_NAME, apiKeyInRequest);
            throw new BadCredentialsException("invalid username or password");
        }
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return ApiKeyAuthentication.class.isAssignableFrom(authentication);
    }

}
