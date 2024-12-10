package com.idontknow.business.adapter.system;

import com.idontknow.business.adapter.base.BaseSystemController;
import com.idontknow.business.application.services.system.OperateLogService;
import com.idontknow.business.application.services.system.dto.*;
import com.idontknow.business.application.services.system.query.SearchOperateLogRequest;
import com.idontknow.business.application.services.system.query.SearchUsersRequest;
import com.idontknow.business.constants.AppUrls;
import com.idontknow.business.domain.entities.system.OperateLogEntity;
import com.idontknow.business.domain.entities.system.RoleEntity;
import com.idontknow.business.domain.entities.system.UserEntity;
import com.idontknow.business.infra.assembler.OperateLogMapper;
import com.idontknow.business.infra.assembler.UsersMapper;
import com.idontknow.business.shared.ApiListPaginationSimple;
import com.idontknow.business.shared.ApiListPaginationSuccess;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(OperateLogController.BASE_URL)
@RequiredArgsConstructor
@Getter
public class OperateLogController{
    public static final String BASE_URL = AppUrls.LOG;
    private final OperateLogService service;
    private final OperateLogMapper mapper;

    @GetMapping
    public ApiListPaginationSuccess<OperateLogResponse> getOperateLog(@RequestBody SearchOperateLogRequest request) {
        return service.getOperateLog(request);
    }
}
