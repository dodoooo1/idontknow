package com.idontknow.business.infra.configs.security.auth.providers;

import com.idontknow.business.core.constants.AppUrls;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Optional;


@Slf4j
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(new AndRequestMatcher(
                new AntPathRequestMatcher("/**"),
                new NegatedRequestMatcher(new OrRequestMatcher(
                        new AntPathRequestMatcher(AppUrls.PUBLIC + "/**"),
                        new AntPathRequestMatcher("/auth/**"),
                        new AntPathRequestMatcher("/v3/api-docs/**"),
                        new AntPathRequestMatcher("/swagger-ui/**"),
                        new AntPathRequestMatcher("/swagger-ui.html")
                ))
        )); // Match all requests initially
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String header = request.getHeader(HEADER_STRING);

            if (header == null || !header.startsWith(TOKEN_PREFIX)) {
                return this.getAuthenticationManager().authenticate(new JwtAuthenticationToken());
            }
            String token = header.replace(TOKEN_PREFIX, "");
            final Optional<String> tokenOptional =
                    StringUtils.hasLength(token) ? Optional.of(token) : Optional.empty();
            final JwtAuthenticationToken authenticationToken =
                    tokenOptional.map(JwtAuthenticationToken::new).orElse(new JwtAuthenticationToken());
            return this.getAuthenticationManager().authenticate(authenticationToken);
        }catch (Exception e){
            throw new BadCredentialsException(e.getMessage());
        }

    }
@Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                             AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        super.unsuccessfulAuthentication(request, response, failed);
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }
}
