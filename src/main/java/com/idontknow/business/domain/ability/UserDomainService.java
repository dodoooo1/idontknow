package com.idontknow.business.domain.ability;

import com.idontknow.business.domain.ability.base.BaseService;
import com.idontknow.business.domain.entities.system.UserEntity;
import com.idontknow.business.domain.gateway.UserEntityGateway;
import com.idontknow.business.enums.StatusEnum;
import com.idontknow.business.infra.assembler.UserEntityMapper;
import com.idontknow.business.infra.configs.security.auth.providers.JwtAuthenticationToken;
import com.idontknow.business.infra.configs.security.auth.providers.SecurityUtils;
import com.idontknow.business.infra.gatewayimpl.repositories.UsersRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class UserDomainService extends BaseService<UserEntity> {

    private final UserEntityGateway gateway;
    private final UserEntityMapper mapper;
    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity create(UserEntity entity) {
        entity.updatePassword(passwordEncoder.encode(entity.getPassword()));
        JwtAuthenticationToken currentUser = SecurityUtils.getCurrentUser().get();
        entity.setCreatedBy(currentUser.getUserInfo().getUsername());
        entity.setUpdatedBy(currentUser.getUserInfo().getUsername());
        entity.setStatus(StatusEnum.INACTIVE);
        return repository.save(entity);
    }

    //更新状态，启用、禁用
    public void updateStatus(UserEntity entity) {
        UserEntity dbEntity = repository.findById(Long.valueOf(entity.getId())).orElseThrow(() -> new RuntimeException("User not exists!"));
        dbEntity.updateStatus(entity.getStatus());
        repository.save(dbEntity);
    }

    /**
     * Update the password for a specific user.
     *
     * @param entity The request containing the user ID and the new password
     */
    public void updatePassword(UserEntity entity) {
        UserEntity dbEntity = repository.findById(entity.getId()).orElseThrow(() -> new RuntimeException("User not exists!"));
        if (!matchesPassword(dbEntity.getPassword(), entity.getPassword())) {
            throw new RuntimeException("New password cannot be the same as the old password");
        }
        String newPassword = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(newPassword);
        repository.save(entity);
    }

    public UserEntity update(UserEntity entity) {
        return repository.save(entity);
    }

    public Optional<UserEntity> loadUserByUsername(String username) {
        return gateway.loadUserByUsername(username);
    }

    public boolean matchesPassword(String loginPassword, String password) {
        return passwordEncoder.matches(loginPassword, password);
    }

    public void bulkDeleteUsers(List<Long> userIds) {
        repository.deleteAllById(userIds);
    }
}
