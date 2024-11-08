package com.idontknow.business.infra.configs.security.auth.providers;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;
import java.util.Optional;

@Slf4j
public class ApiKeyAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "Authorization";

    public ApiKeyAuthenticationFilter(
            final String defaultFilterProcessesUrl, final AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl);
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(
            final HttpServletRequest request, final HttpServletResponse response) {
        Optional<String> token = Optional.empty();
        String header = request.getHeader(HEADER_STRING);
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
             token = Optional.of(header.replace(TOKEN_PREFIX, ""));
        } else {
            logger.warn("couldn't find bearer string, will ignore the header");
        }
        final ApiKeyAuthentication apiKey =
                token.map(ApiKeyAuthentication::new).orElse(new ApiKeyAuthentication());
        return this.getAuthenticationManager().authenticate(apiKey);
    }

    @Override
    protected void successfulAuthentication(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain chain,
            final Authentication authResult)
            throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }
}
