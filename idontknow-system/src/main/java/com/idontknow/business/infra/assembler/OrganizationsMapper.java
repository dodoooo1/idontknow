package com.idontknow.business.infra.assembler;


import com.idontknow.business.application.services.system.dto.CreateOrganizationsRequest;
import com.idontknow.business.application.services.system.dto.OrganizationsResponse;
import com.idontknow.business.application.services.system.dto.UpdateOrganizationsRequest;
import com.idontknow.business.domain.entities.system.OrganizationEntity;
import com.idontknow.business.infra.assembler.base.SystemBaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrganizationsMapper
        extends SystemBaseMapper<
        OrganizationEntity,
        CreateOrganizationsRequest,
        UpdateOrganizationsRequest,
        OrganizationsResponse> {
    @Override
    OrganizationEntity toEntity(CreateOrganizationsRequest request);

    @Override
    OrganizationsResponse toSystemResponse(OrganizationEntity entity);

}
