package com.idontknow.business.adapter.system;

import com.idontknow.business.adapter.base.BaseSystemController;
import com.idontknow.business.application.services.system.OrganizationsService;
import com.idontknow.business.application.services.system.dto.*;
import com.idontknow.business.application.services.system.query.SearchDictionariesRequest;
import com.idontknow.business.application.services.system.query.SearchOrganizationsRequest;
import com.idontknow.business.application.services.system.query.SearchUsersRequest;
import com.idontknow.business.constants.AppUrls;
import com.idontknow.business.domain.entities.system.OrganizationEntity;
import com.idontknow.business.domain.entities.system.RoleEntity;
import com.idontknow.business.infra.assembler.OrganizationsMapper;
import com.idontknow.business.shared.ApiListPaginationSimple;
import com.idontknow.business.shared.ApiListPaginationSuccess;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(OrganizationsController.BASE_URL)
@RequiredArgsConstructor
@Getter
public class OrganizationsController extends BaseSystemController<
        OrganizationEntity,
        CreateOrganizationsRequest,
        UpdateOrganizationsRequest,
        OrganizationsResponse> {
    public static final String BASE_URL = AppUrls.ORGANIZATION;
    private final OrganizationsService service;
    private final OrganizationsMapper mapper;

    @GetMapping
    public ApiListPaginationSuccess<OrganizationsResponse> getOrganizations(@RequestBody SearchOrganizationsRequest request) {
        return service.getOrganizations(request);
    }
}
