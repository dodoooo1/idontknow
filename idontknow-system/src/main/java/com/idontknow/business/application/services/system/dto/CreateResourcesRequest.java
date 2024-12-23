package com.idontknow.business.application.services.system.dto;

public record CreateResourcesRequest(

        Long parentId,
        String name,
        String url,
        String component,
        Boolean isRoute,
        String level,
        String code,
        String icon,
        Boolean keepAlive,
        Boolean hidden,
        String openType
) {

}
