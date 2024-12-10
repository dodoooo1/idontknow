package com.idontknow.business.application.services.system;

import com.idontknow.business.application.dto.CustomUserDetails;
import com.idontknow.business.application.services.system.UsersService;
import com.idontknow.business.application.services.system.dto.CreateUsersRequest;
import com.idontknow.business.application.services.system.dto.LoginRequest;
import com.idontknow.business.domain.entities.system.OrganizationEntity;
import com.idontknow.business.domain.entities.system.UserEntity;
import com.idontknow.business.infra.assembler.UsersMapper;
import com.idontknow.business.infra.configs.security.auth.providers.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

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
public class AuthenticationService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;
    private final UsersService usersService;
    private final UsersMapper mapper;

    /**
     * Authenticates a user with the provided login request.
     *
     * @param loginRequest the login request containing the user's credentials
     * @return a string representing the authentication token
     */
    public String authenticate(@Valid LoginRequest loginRequest) {
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(loginRequest.username());
        if (!usersService.matchesPassword(loginRequest.password(), userDetails.getPassword())) {
            throw new RuntimeException("Invalid UserName or password!");
        }
        //部门Set集合中是存在这个某个部门id
        if (userDetails.getOrganizations().stream().noneMatch(organizationEntity -> organizationEntity.getId().equals(loginRequest.organizationId()))) {
            throw new RuntimeException("Invalid organization id!");
        }
        userDetails.setCurrentOrganization(userDetails.getOrganizations().stream().filter(organizationEntity -> organizationEntity.getId().equals(loginRequest.organizationId())).findFirst().orElseThrow());
        return jwtTokenProvider.generateToken(userDetails);
    }
    /**
     * Authenticates a user with the provided login request.
     *
     * @param loginRequest the login request containing the user's credentials
     * @return a string representing the authentication token
     */
    public Set<OrganizationEntity> checkMultiOrganization(@Valid LoginRequest loginRequest) {
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(loginRequest.username());
        if (!usersService.matchesPassword(loginRequest.password(), userDetails.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return userDetails.getOrganizations();
    }

    /**
     * Signs up a new system user with the provided request.
     *
     * @param createUsersRequest the request containing the information of the user to sign up
     */
    public void signup(CreateUsersRequest createUsersRequest) {
        UserEntity entity = mapper.toEntity(createUsersRequest);
        usersService.create(entity);
    }
}
