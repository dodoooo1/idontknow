package com.idontknow.business.infra.configs.security;

import com.idontknow.business.constants.AppUrls;
import com.idontknow.business.infra.configs.security.auth.providers.ApiKeyAuthenticationFilter;
import com.idontknow.business.infra.configs.security.auth.providers.ApiKeyAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import java.util.Collections;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider apiKeyAuthenticationProvider() {
        return new ApiKeyAuthenticationProvider(userDetailsService, passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(
                Collections.singletonList(this.apiKeyAuthenticationProvider()));
    }

    @Bean
    public ApiKeyAuthenticationFilter apiKeyAuthenticationFilter() {
        return new ApiKeyAuthenticationFilter(
                AppUrls.PLATFORM_API + "/**", this.authenticationManager());

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(apiKeyAuthenticationFilter(), AnonymousAuthenticationFilter.class)
                .authorizeHttpRequests(
                        authorize ->
                                authorize

                                        .requestMatchers(AppUrls.PUBLIC + "/**", "/auth/**", "/v3/api-docs/**",
                                                "/swagger-ui/**", "/swagger-ui.html")
                                        .permitAll()
                                        //
                                        .anyRequest()
                                        .denyAll())
                // To prevent any misconfiguration we disable explicitly all authentication scheme
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
