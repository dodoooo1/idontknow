package com.idontknow.business.domain.ability;

import com.idontknow.business.domain.ability.base.BaseDomainService;
import com.idontknow.business.domain.entities.system.DictionaryEntity;
import com.idontknow.business.infra.assembler.DictionariesMapper;
import com.idontknow.business.infra.configs.security.auth.providers.JwtAuthenticationToken;
import com.idontknow.business.infra.configs.security.auth.providers.SecurityUtils;
import com.idontknow.business.infra.gatewayimpl.repositories.DictionariesRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @description:
 * @title: DictionariesDomainService
 * @package com.idontknow.business.domain.ability
 * @author: glory
 * @date: 2024/12/2 21:21
 */
@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class DictionariesDomainService extends BaseDomainService<DictionaryEntity> {

    private final DictionariesMapper mapper;
    private final DictionariesRepository repository;

    public DictionaryEntity create(DictionaryEntity entity) {
        JwtAuthenticationToken currentUser = SecurityUtils.getCurrentUser().get();
        Assert.notNull(currentUser, "currentUser is null");
        entity.setCreatedBy(currentUser.getUserInfo().getUsername());
        entity.setUpdatedBy(currentUser.getUserInfo().getUsername());
        return repository.save(entity);
    }


    public DictionaryEntity update(DictionaryEntity entity) {
        return repository.save(entity);
    }

}
