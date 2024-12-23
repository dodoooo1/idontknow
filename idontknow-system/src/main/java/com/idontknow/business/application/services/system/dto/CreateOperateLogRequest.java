package com.idontknow.business.application.services.system.dto;

public record CreateOperateLogRequest(
        String moduleName,
        String method,
        String requestMethod,
        String operatorType,
        String operUrl,
        String requestParam,
        Long costTime
) {


    // getters and setters
}
