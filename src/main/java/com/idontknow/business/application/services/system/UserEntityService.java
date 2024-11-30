package com.idontknow.business.application.services.system;

import com.idontknow.business.application.services.system.dto.UpdateUserEntityRequest;
import com.idontknow.business.domain.entities.system.UserEntity;

import java.util.List;

/**
 * Interface for managing system users.
 */
public interface UserEntityService {
    /**
     * Creates a new system user based on the provided request.
     *
     * @param entity the request containing the information of the user to create
     */
    UserEntity create(UserEntity entity);

    UserEntity findById(String id);

    /**
     * @param updateUserEntityRequest
     */
    void updateStatus(UpdateUserEntityRequest updateUserEntityRequest);

    UserEntity loadUserByUsername(String username);

    void update(UpdateUserEntityRequest updateUserEntityRequest);

    void delete(String id);

    void deleteAllById(List<String> idList);

    boolean matchesPassword(String loginPassword, String password);

    void updatePassword(UpdateUserEntityRequest updateUserEntityRequest);
}
