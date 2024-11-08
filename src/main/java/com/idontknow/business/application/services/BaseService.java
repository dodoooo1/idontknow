package com.idontknow.business.application.services;

/**
 * @description:
 * @title: BaseService
 * @package com.idontknow.business.application.services
 * @author: glory
 * @date: 2024/10/24 10:29
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
public abstract class BaseService<E> {


    @Autowired
    public ApplicationEventPublisher applicationEventPublisher;

}

