package com.idontknow.business.infra.assembler;


import com.idontknow.business.application.services.system.dto.CreateUserEntityRequest;
import com.idontknow.business.application.services.system.dto.SysUserResponse;
import com.idontknow.business.application.services.system.dto.UpdateUserEntityRequest;
import com.idontknow.business.domain.entities.system.UserEntity;
import com.idontknow.business.infra.assembler.base.SystemBaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper
        extends SystemBaseMapper<
        UserEntity,
        CreateUserEntityRequest,
        UpdateUserEntityRequest,
        SysUserResponse> {
    @Override
    UserEntity toEntity(CreateUserEntityRequest request);

    @Override
    SysUserResponse toSystemResponse(UserEntity entity);

}
