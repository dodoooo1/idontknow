package com.idontknow.business.infra.gatewayimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idontknow.business.domain.entities.system.QUserEntity;
import com.idontknow.business.domain.entities.system.UserEntity;
import com.idontknow.business.domain.gateway.UserEntityGateway;
import com.idontknow.business.infra.assembler.UsersMapper;
import com.idontknow.business.infra.gatewayimpl.repositories.UsersRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @description:
 * @title: SysUserGatewayImpl
 * @package com.idontknow.business.infra.gatewayimpl
 * @author: glory
 * @date: 2024/10/23 15:46
 */
@Getter
@Slf4j
@Service
@RequiredArgsConstructor
public class UserEntityGatewayImpl implements UserEntityGateway {
    private final UsersRepository repository;
    private final JPAQueryFactory jpaQueryFactory;
    private final UsersMapper mapper;

    @Override
    public boolean isUsernameTaken(String username) {
        return false;
    }

    @Override
    public boolean isEmailTaken(String email) {
        QUserEntity qUserEntity = QUserEntity.userEntity;
        List<Long> list = jpaQueryFactory.select(qUserEntity.id).from(qUserEntity).where(qUserEntity.email.eq(email)).fetch();
        return list.isEmpty();
    }

    public void create(UserEntity sysUser) {

        try {
            log.info("save user: {}", new ObjectMapper().writeValueAsString(sysUser));
            log.info("sysUserEntity user: {}", new ObjectMapper().writeValueAsString(sysUser));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        repository.save(sysUser);

    }

    @Override
    public void update(UserEntity sysUser) {
        repository.save(sysUser);
    }

    @Override
    public void delete(Long[] ids) {

    }

    @Override
    public Optional<UserEntity> loadUserByUsername(String username) {
        QUserEntity qUserEntity = QUserEntity.userEntity;
        BooleanExpression predicate = qUserEntity.username.eq(username);
        return repository.findOne(predicate);
    }

    @Override
    public UserEntity findById(Long id) {
        return null;
    }

}
