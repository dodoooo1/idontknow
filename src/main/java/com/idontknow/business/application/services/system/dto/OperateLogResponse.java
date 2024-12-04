package com.idontknow.business.application.services.system.dto;

public record OperateLogResponse(Long id, String moduleName, String method, String requestMethod, String operUrl,
                                 String operatorType, String requestParam, String errorMsg, Long costTime) {

}
