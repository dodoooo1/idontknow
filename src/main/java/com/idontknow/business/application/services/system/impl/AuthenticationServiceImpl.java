package com.idontknow.business.application.services.system.impl;

import com.idontknow.business.application.dto.CustomUserDetails;
import com.idontknow.business.application.services.system.AuthenticationService;
import com.idontknow.business.application.services.system.UserEntityService;
import com.idontknow.business.application.services.system.dto.CreateUserEntityRequest;
import com.idontknow.business.application.services.system.dto.LoginRequest;
import com.idontknow.business.domain.entities.system.UserEntity;
import com.idontknow.business.infra.assembler.UserEntityMapper;
import com.idontknow.business.infra.configs.security.auth.providers.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @title: AuthenticationServiceImpl
 * @package com.idontknow.business.application.services.system.query.impl
 * @author: glory
 * @date: 2024/10/25 09:48
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;
    private final UserEntityService userEntityService;
    private final UserEntityMapper mapper;

    /**
     * Authenticates a user with the provided login request.
     *
     * @param loginRequest the login request containing the user's credentials
     * @return a string representing the authentication token
     */
    public String authenticate(@Valid LoginRequest loginRequest) {
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(loginRequest.username());
        if (!userEntityService.matchesPassword(loginRequest.password(), userDetails.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return jwtTokenProvider.generateToken(userDetails);
    }

    /**
     * Signs up a new system user with the provided request.
     *
     * @param createUserEntityRequest the request containing the information of the user to sign up
     */
    @Override
    public void signup(CreateUserEntityRequest createUserEntityRequest) {
        UserEntity entity = mapper.toEntity(createUserEntityRequest);
        userEntityService.create(entity);
    }
}
