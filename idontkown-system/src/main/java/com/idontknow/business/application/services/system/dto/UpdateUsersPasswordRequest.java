package com.idontknow.business.application.services.system.dto;

/**
 * @description: 更新密码
 * @title: UpdatePasswordRequest
 * @package com.idontknow.business.application.services.system.dto
 * @author: glory
 * @date: 2024/12/1 16:28
 */
public record UpdateUsersPasswordRequest(Long id, String password, String newPassword) {
}
