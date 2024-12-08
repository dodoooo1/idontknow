package com.idontknow.business.adapter.system;


import com.idontknow.business.adapter.base.BaseSystemController;
import com.idontknow.business.application.services.system.UsersService;
import com.idontknow.business.application.services.system.dto.CreateUsersRequest;
import com.idontknow.business.application.services.system.dto.UpdateUsersRequest;
import com.idontknow.business.application.services.system.dto.UsersResponse;
import com.idontknow.business.application.services.system.query.SearchUsersRequest;
import com.idontknow.business.constants.AppUrls;
import com.idontknow.business.domain.entities.system.UserEntity;
import com.idontknow.business.infra.assembler.UsersMapper;
import com.idontknow.business.shared.ApiListPaginationSuccess;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Getter
@Slf4j
@RestController
@RequestMapping(UsersController.BASE_URL)
@RequiredArgsConstructor
public class UsersController extends BaseSystemController<
        UserEntity,
        CreateUsersRequest,
        UpdateUsersRequest,
        UsersResponse> {

    private final UsersService service;
    private final UsersMapper mapper;
    public static final String BASE_URL = AppUrls.USER;

    @GetMapping
    public ApiListPaginationSuccess<UsersResponse> getUsers(@RequestBody SearchUsersRequest request) {
        return service.getUsers(request);
    }
}
