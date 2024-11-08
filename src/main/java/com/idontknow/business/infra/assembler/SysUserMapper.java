package com.idontknow.business.infra.assembler;


import com.idontknow.business.application.services.system.command.cmd.CreateSysUserRequest;
import com.idontknow.business.application.services.system.command.cmd.UpdateSysUserRequest;
import com.idontknow.business.application.services.system.query.qry.SysUserResponse;
import com.idontknow.business.domain.entities.system.SysUser;
import com.idontknow.business.infra.assembler.base.BaseMapper;
import com.idontknow.business.infra.gatewayimpl.dataobject.system.SysUserDO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SysUserMapper
        extends BaseMapper<
        SysUser,
        SysUserDO,
        CreateSysUserRequest,
        UpdateSysUserRequest,
        SysUserResponse> {
}
