package com.idontknow.business.infra.assembler;


import com.idontknow.business.application.services.system.dto.CreateUsersRequest;
import com.idontknow.business.application.services.system.dto.UpdateUsersRequest;
import com.idontknow.business.application.services.system.dto.UsersResponse;
import com.idontknow.business.domain.entities.system.UserEntity;
import com.idontknow.business.infra.assembler.base.SystemBaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper
        extends SystemBaseMapper<
        UserEntity,
        CreateUsersRequest,
        UpdateUsersRequest,
        UsersResponse> {
    @Override
    UserEntity toEntity(CreateUsersRequest request);

    @Override
    UsersResponse toSystemResponse(UserEntity entity);

}
