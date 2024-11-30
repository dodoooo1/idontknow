package com.idontknow.business.infra.configs.security.auth.providers;

import com.idontknow.business.application.dto.CustomUserDetails;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Objects;

@Setter
@RequiredArgsConstructor
@Slf4j

public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getPrincipal();
        // Validate the JWT token here
        if (jwtTokenProvider.isTokenExpired(token)) {
            throw new BadCredentialsException("Invalid JWT token");
        }
        Claims claims = jwtTokenProvider.extractAllClaims(token);
        String username = claims.getSubject();
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
        if (Objects.isNull(userDetails)) {
            throw new BadCredentialsException("Invalid JWT token");
        }
        JwtAuthenticationToken.UserInfo userInfo = JwtAuthenticationToken.UserInfo.builder().id(userDetails.getId())
                .name(userDetails.getName())
                .email(userDetails.getEmail())
                .username(username).build();

        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(token, true, userInfo, userDetails.getAuthorities());
        // 设置 SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }

}
