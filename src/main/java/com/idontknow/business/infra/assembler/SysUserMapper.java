package com.idontknow.business.infra.assembler;


import com.idontknow.business.application.services.system.dto.CreateSysUserRequest;
import com.idontknow.business.application.services.system.dto.SysUserResponse;
import com.idontknow.business.application.services.system.dto.UpdateSysUserRequest;
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
