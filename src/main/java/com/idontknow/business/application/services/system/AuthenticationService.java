package com.idontknow.business.application.services.system;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.idontknow.business.application.services.system.dto.CreateUserEntityRequest;
import com.idontknow.business.application.services.system.dto.LoginRequest;

/**
 * Interface for authenticating users and signing up new system users.
 */
public interface AuthenticationService {
    /**
     * Authenticates a user with the provided login request.
     *
     * @param loginRequest the login request containing the user's credentials
     * @return a string representing the authentication token
     * @throws JsonProcessingException if an error occurs during JSON processing
     */
    String authenticate(LoginRequest loginRequest) throws JsonProcessingException;

    /**
     * Sign up a new system user with the provided request.
     *
     * @param createUserEntityRequest the request containing the information of the user to sign up
     */
    void signup(CreateUserEntityRequest createUserEntityRequest);

}
