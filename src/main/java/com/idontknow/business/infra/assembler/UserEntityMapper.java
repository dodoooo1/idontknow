package com.idontknow.business.infra.assembler;


import com.idontknow.business.application.services.system.dto.CreateUsersRequest;
import com.idontknow.business.application.services.system.dto.UpdateUsersRequest;
import com.idontknow.business.application.services.system.dto.UserEntityResponse;
import com.idontknow.business.domain.entities.system.UserEntity;
import com.idontknow.business.infra.assembler.base.SystemBaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper
        extends SystemBaseMapper<
        UserEntity,
        CreateUsersRequest,
        UpdateUsersRequest,
        UserEntityResponse> {
    @Override
    UserEntity toEntity(CreateUsersRequest request);

    @Override
    UserEntityResponse toSystemResponse(UserEntity entity);

}
