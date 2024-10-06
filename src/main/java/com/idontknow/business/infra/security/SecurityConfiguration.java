package com.idontknow.business.infra.security;

import static com.idontknow.business.enums.UserRolesEnum.*;

import com.idontknow.business.constants.AppUrls;
import com.idontknow.business.infra.auth.providers.ApiKeyAuthenticationFilter;
import com.idontknow.business.infra.auth.providers.ApiKeyAuthenticationProvider;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Slf4j
@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  public AuthenticationProvider companyApiKeyAuthenticationProvider() {
    return new ApiKeyAuthenticationProvider();
  }

  @Bean
  public AuthenticationManager authenticationManager() {
    return new ProviderManager(
        Collections.singletonList(this.companyApiKeyAuthenticationProvider()));
  }

  @Bean
  public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
    http.addFilterBefore(
            new ApiKeyAuthenticationFilter(AppUrls.INTERNAL + "/**", this.authenticationManager()),
            AnonymousAuthenticationFilter.class)
        .addFilterBefore(
            new ApiKeyAuthenticationFilter(
                AppUrls.PLATFORM_API + "/**", this.authenticationManager()),
            AnonymousAuthenticationFilter.class)
        .authorizeHttpRequests(
            authorize ->
                authorize
                    //
                    .requestMatchers(AppUrls.PLATFORM_WEB + "/**", AppUrls.PLATFORM_MOBILE + "/**")
                    .hasAnyRole(PLATFORM_USER.getName(), PLATFORM_ADMIN.getName())
                    .requestMatchers(AppUrls.PLATFORM_API + "/**")
                    .hasAnyRole(PLATFORM_API_USER.getName())
                    //
                    .requestMatchers(AppUrls.MANAGEMENT + "/**")
                    .hasAnyRole(MANAGEMENT_USER.getName(), MANAGEMENT_ADMIN.getName())
                    //
                    .requestMatchers(AppUrls.INTERNAL + "/**")
                    .hasAnyRole(INTERNAL_API_USER.getName())
                    //
                    .requestMatchers(AppUrls.PUBLIC + "/**")
                    .permitAll()
                    //
                    .requestMatchers("/actuator/**")
                    .permitAll()
                    //
                    .anyRequest()
                    .denyAll())
        // Necessary if we want to be able to call POST/PUT/DELETE
        .csrf(AbstractHttpConfigurer::disable)
        // To prevent any misconfiguration we disable explicitly all authentication scheme
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .httpBasic(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .logout(AbstractHttpConfigurer::disable);

    return http.build();
  }
}
