package com.idontknow.business.application.services.base;

import com.idontknow.business.application.services.system.dto.RolesResponse;
import com.idontknow.business.application.services.system.query.SearchRolesRequest;
import com.idontknow.business.domain.ability.base.BaseDomainService;
import com.idontknow.business.domain.entities.base.BaseEntity;
import com.idontknow.business.shared.ApiListPaginationSuccess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description:
 * @title: BaseService
 * @package com.idontknow.business.application.services.base
 * @author: glory
 * @date: 2024/12/4 21:52
 */

@Slf4j
@Transactional(rollbackFor = Exception.class)
public abstract class BaseService<E extends BaseEntity> {

    private static final int ENTITY_MAX_SIZE_TO_LOG = 100;

    public abstract BaseDomainService<E> getDomainService();

    public E findById(Long id) {
        return this.getDomainService().findById(id);
    }

    public void delete(Long id) {
        getDomainService().delete(id);
    }

}
