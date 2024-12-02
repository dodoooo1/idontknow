package com.idontknow.business.application.dto;

import com.idontknow.business.enums.StatusEnum;

import java.util.List;

/**
 * @param ids
 * @param status
 */
public record UpdateStatusRequest(List<Long> ids, StatusEnum status) {
}
