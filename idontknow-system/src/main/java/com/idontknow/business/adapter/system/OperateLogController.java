package com.idontknow.business.adapter.system;

import com.idontknow.business.application.services.system.OperateLogService;
import com.idontknow.business.application.services.system.dto.OperateLogResponse;
import com.idontknow.business.application.services.system.query.SearchOperateLogRequest;
import com.idontknow.business.core.constants.AppUrls;
import com.idontknow.business.infra.assembler.OperateLogMapper;
import com.idontknow.business.jpa.ApiListPaginationSuccess;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(OperateLogController.BASE_URL)
@RequiredArgsConstructor
@Getter
public class OperateLogController {
    public static final String BASE_URL = AppUrls.LOG;
    private final OperateLogService service;
    private final OperateLogMapper mapper;

    @GetMapping
    public ApiListPaginationSuccess<OperateLogResponse> getOperateLog(@RequestBody SearchOperateLogRequest request) {
        return service.getOperateLog(request);
    }
}
