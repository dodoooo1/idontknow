package com.idontknow.business.adapter.system;

import com.idontknow.business.adapter.base.BaseSystemController;
import com.idontknow.business.application.services.system.RolesService;
import com.idontknow.business.application.services.system.dto.CreateRolesRequest;
import com.idontknow.business.application.services.system.dto.RolesResponse;
import com.idontknow.business.application.services.system.dto.UpdateRolesRequest;
import com.idontknow.business.application.services.system.query.SearchRolesRequest;
import com.idontknow.business.constants.AppUrls;
import com.idontknow.business.domain.entities.system.RoleEntity;
import com.idontknow.business.infra.assembler.RolesMapper;
import com.idontknow.business.shared.ApiListPaginationSuccess;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(RolesController.BASE_URL)
@RequiredArgsConstructor
@Getter
public class RolesController extends BaseSystemController<
        RoleEntity,
        CreateRolesRequest,
        UpdateRolesRequest,
        RolesResponse> {
    public static final String BASE_URL = AppUrls.ROLE;
    private final RolesService service;
    private final RolesMapper mapper;

    @GetMapping
    public ApiListPaginationSuccess<RolesResponse> getRoles(@RequestBody SearchRolesRequest request) {
        return service.getRoles(request);
    }

}
