package com.idontknow.business.infra.assembler;


import com.idontknow.business.application.services.system.command.cmd.CreateSysUserRequest;
import com.idontknow.business.application.services.system.command.cmd.UpdateSysUserRequest;
import com.idontknow.business.application.services.system.query.qry.SysUserResponse;
import com.idontknow.business.domain.entities.system.SysUserEntity;
import com.idontknow.business.infra.assembler.base.BaseMapper;
import com.idontknow.business.infra.gatewayimpl.dataobject.system.SysUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SysUserMapper
    extends BaseMapper<
        SysUserEntity,
        SysUserDO,
        CreateSysUserRequest,
        UpdateSysUserRequest,
        SysUserResponse> {
    @Override
    SysUserEntity toEntity(CreateSysUserRequest request);
}
