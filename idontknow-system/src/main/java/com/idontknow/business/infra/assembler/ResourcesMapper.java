package com.idontknow.business.infra.assembler;

import com.idontknow.business.application.services.system.dto.CreateResourcesRequest;
import com.idontknow.business.application.services.system.dto.ResourcesResponse;
import com.idontknow.business.application.services.system.dto.UpdateResourcesRequest;
import com.idontknow.business.domain.entities.system.ResourceEntity;
import com.idontknow.business.infra.assembler.base.SystemBaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResourcesMapper
        extends SystemBaseMapper<
                ResourceEntity,
                CreateResourcesRequest,
                UpdateResourcesRequest,
                ResourcesResponse> {
    @Override
    ResourceEntity toEntity(CreateResourcesRequest request);

    @Override
    ResourcesResponse toSystemResponse(ResourceEntity entity);

}

