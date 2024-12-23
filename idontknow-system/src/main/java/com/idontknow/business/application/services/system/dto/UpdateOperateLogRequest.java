package com.idontknow.business.application.services.system.dto;

public record UpdateOperateLogRequest(Long id, String moduleName, Integer businessType, String method,
                                      String requestMethod, String operatorType, String operUrl, String requestParam,
                                      String response, Long costTime) {

}
