package com.idontknow.business.adapter.system;

import com.idontknow.business.adapter.base.BaseSystemController;
import com.idontknow.business.application.services.system.ResourcesService;
import com.idontknow.business.application.services.system.dto.CreateResourcesRequest;
import com.idontknow.business.application.services.system.dto.ResourcesResponse;
import com.idontknow.business.application.services.system.dto.UpdateResourcesRequest;
import com.idontknow.business.application.services.system.query.SearchResourcesRequest;
import com.idontknow.business.application.services.system.query.SearchUsersRequest;
import com.idontknow.business.constants.AppUrls;
import com.idontknow.business.domain.entities.system.ResourceEntity;
import com.idontknow.business.infra.assembler.base.ResourcesMapper;
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
@RequestMapping(ResourcesController.BASE_URL)
@RequiredArgsConstructor
@Getter
public class ResourcesController extends BaseSystemController<
        ResourceEntity,
        CreateResourcesRequest,
        UpdateResourcesRequest,
        ResourcesResponse> {
    public static final String BASE_URL = AppUrls.RESOURCES;
    private final ResourcesService service;
    private final ResourcesMapper mapper;

    @GetMapping
    public ApiListPaginationSuccess<ResourcesResponse> getResources(@RequestBody SearchResourcesRequest request) {
        return service.getResources(request);
    }
}
