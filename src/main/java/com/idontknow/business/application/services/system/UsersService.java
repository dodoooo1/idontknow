package com.idontknow.business.application.services.system;

import com.idontknow.business.application.services.system.dto.SearchUsersRequest;
import com.idontknow.business.application.services.system.dto.UpdateUsersRequest;
import com.idontknow.business.application.services.system.dto.UserEntityResponse;
import com.idontknow.business.domain.entities.system.UserEntity;
import com.idontknow.business.shared.ApiListPaginationSimple;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Interface for managing system users.
 */
public interface UsersService {
    /**
     * Creates a new system user based on the provided request.
     *
     * @param entity the request containing the information of the user to create
     */
    UserEntity create(UserEntity entity);

    UserEntity findById(String id);

    /**
     * @param updateUsersRequest
     */
    void updateStatus(UpdateUsersRequest updateUsersRequest);

    UserEntity loadUserByUsername(String username);

    void update(UpdateUsersRequest updateUsersRequest);

    void delete(String id);


    boolean matchesPassword(String loginPassword, String password);

    void updatePassword(UpdateUsersRequest updateUsersRequest);

    void bulkDeleteUsers(List<Long> userIds);

    ApiListPaginationSimple<UserEntityResponse> listByPage(SearchUsersRequest request);
}
