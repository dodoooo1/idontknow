package com.idontknow.business.application.services.system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.idontknow.business.application.dto.UpdateStatusRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * @description: 新增用户
 * @title: CreateSysUserRequest
 * @package com.idontknow.business.application.services.command.cmd
 * @author: glory
 * @date: 2024/10/24 10:13
 */

public record UpdateUsersRequest(

        /**
         * 幂等性key,这里不需要，因为用户更新时，idempotencyKey是可选的，只有更新数量、金额是才必须的
         */
        //String idempotencyKey,
        /**
         * 版本号,更新时，版本号不能为空
         */
        int version,
        @NotBlank(message = "id不能为空")
        String id,
        @NotBlank(message = "用户名不能为空")
        String username,
        String phone,
        String avatar,
        String nickname,
        @NotBlank(message = "姓名不能为空")
        String name,
        @Email(message = "邮件格式错误")
        String email,
        @JsonProperty("updatePassword")
        UpdateUsersPasswordRequest updateUsersPasswordRequest,
        @JsonProperty("updateStatus")
        UpdateStatusRequest updateStatusRequest
) {

}
