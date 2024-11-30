package com.idontknow.business.application.services.system.impl;

import com.idontknow.business.application.services.system.UserEntityService;
import com.idontknow.business.application.services.system.dto.UpdateUserEntityRequest;
import com.idontknow.business.domain.ability.RoleDomainService;
import com.idontknow.business.domain.ability.UserDomainService;
import com.idontknow.business.domain.entities.system.UserEntity;
import com.idontknow.business.infra.assembler.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @title: UserService
 * @package com.idontknow.business.application.services.query
 * @author: glory
 * @date: 2024/10/23 16:00
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserEntityServiceImpl implements UserEntityService {
    private final UserEntityMapper mapper;
    private final UserDomainService userDomainService;
    private final RoleDomainService roleDomainService;


    @Override
    public UserEntity create(UserEntity userEntity) {
        return userDomainService.create(userEntity);
    }


    @Override
    public UserEntity findById(String id) {
        return userDomainService.findById(Long.valueOf(id));
    }

    /**
     * @param updateUserEntityRequest
     */
    @Override
    public void updateStatus(UpdateUserEntityRequest updateUserEntityRequest) {
        UserEntity dbEntity = userDomainService.findById(Long.valueOf(updateUserEntityRequest.id()));
        dbEntity.updateStatus(updateUserEntityRequest.status());
        userDomainService.update(dbEntity);
    }

    @Override
    public UserEntity loadUserByUsername(String username) {
        return userDomainService.loadUserByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void update(UpdateUserEntityRequest updateUserEntityRequest) {
        UserEntity dbEntity = userDomainService.findById(Long.valueOf(updateUserEntityRequest.id()));
        userDomainService.update(mapper.update(updateUserEntityRequest, dbEntity));
    }

    @Override
    public void delete(String id) {
        userDomainService.delete(Long.valueOf(id));
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteAllById(List<String> idList) {
        userDomainService.deleteAllById(idList.stream()
                .map(Long::parseLong)
                .collect(Collectors.toList()));
    }

    @Override
    public boolean matchesPassword(String loginPassword, String password) {
        return userDomainService.matchesPassword(loginPassword, password);
    }

    @Override
    public void updatePassword(UpdateUserEntityRequest updateUserEntityRequest) {
        UserEntity dbEntity = userDomainService.findById(Long.valueOf(updateUserEntityRequest.id()));
        userDomainService.updatePassword(mapper.update(updateUserEntityRequest, dbEntity));
    }
}
