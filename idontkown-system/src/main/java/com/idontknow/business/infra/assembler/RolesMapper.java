package com.idontknow.business.infra.assembler;

import com.idontknow.business.application.services.system.dto.CreateRolesRequest;
import com.idontknow.business.application.services.system.dto.RolesResponse;
import com.idontknow.business.application.services.system.dto.UpdateRolesRequest;
import com.idontknow.business.domain.entities.system.RoleEntity;
import com.idontknow.business.infra.assembler.base.SystemBaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolesMapper
        extends SystemBaseMapper<
        RoleEntity,
        CreateRolesRequest,
        UpdateRolesRequest,
        RolesResponse> {
    @Override
    RoleEntity toEntity(CreateRolesRequest request);

    @Override
    RolesResponse toSystemResponse(RoleEntity entity);

}

