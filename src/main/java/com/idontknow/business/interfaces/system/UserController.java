package com.idontknow.business.interfaces.system;


import com.idontknow.business.application.services.system.SysUserService;
import com.idontknow.business.application.services.system.command.cmd.CreateSysUserRequest;
import com.idontknow.business.application.services.system.command.cmd.UpdateSysUserRequest;
import com.idontknow.business.application.services.system.query.qry.SysUserResponse;
import com.idontknow.business.constants.AppUrls;
import com.idontknow.business.domain.entities.system.SysUserEntity;
import com.idontknow.business.infra.assembler.SysUserMapper;
import com.idontknow.business.infra.gatewayimpl.dataobject.system.SysUserDO;
import com.idontknow.business.interfaces.BaseController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(UserController.BASE_URL)
@RequiredArgsConstructor
@Getter
public class UserController extends BaseController<
        SysUserEntity,
        SysUserDO,
        CreateSysUserRequest,
        UpdateSysUserRequest,
        SysUserResponse> {

    private final SysUserService sysUserService;
    public static final String BASE_URL = AppUrls.USER;
    @Getter
    private final SysUserMapper mapper;
    @GetMapping
    public ResponseEntity<SysUserResponse> userProfile() {
        return ResponseEntity.ok(mapper.toResponse(sysUserService.getCurrentUser()));
    }
}
