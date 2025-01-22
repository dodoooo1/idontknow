package com.idontknow.business.infra.configs.security.auth.providers;

import com.google.common.collect.Sets;
import com.idontknow.business.application.dto.CustomUserDetails;
import com.idontknow.business.domain.entities.system.*;
import com.idontknow.business.infra.gatewayimpl.repositories.RolesRepository;
import com.idontknow.business.infra.gatewayimpl.repositories.UsersRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Component
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsersRepository usersRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
   // @Cacheable(value = "userDetails", key = "#username")
    public CustomUserDetails loadUserByUsername(String username) {
        QUserEntity qUser = QUserEntity.userEntity;
        BooleanExpression expression = qUser.username.eq(username).or(qUser.email.eq(username));
        UserEntity user = usersRepository.findOne(expression).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username or email: " + username));

        QUserOrganizationEntity qUserOrganization = QUserOrganizationEntity.userOrganizationEntity;
        QOrganizationEntity qOrganization = QOrganizationEntity.organizationEntity;
        QUserRoleEntity qUserRole = QUserRoleEntity.userRoleEntity;
        QRoleEntity qRole = QRoleEntity.roleEntity;
        Set<OrganizationEntity> orgList = Sets.newHashSet(jpaQueryFactory.select(qOrganization)
                .from(qOrganization)
                .join(qUserOrganization).on(qOrganization.id.eq(qUserOrganization.orgId))
                .join(qUser).on(qUser.id.eq(qUserOrganization.userId))
                .where(qUser.id.eq(user.getId()))
                .fetch());
        Set<RoleEntity> roleList =Sets.newHashSet( jpaQueryFactory.select(qRole)
                .from(qRole)
                .join(qUserRole).on(qRole.id.eq(qUserRole.roleId))
                .join(qUser).on(qUser.id.eq(qUserRole.userId))
                .where(qUser.id.eq(user.getId()))
                .fetch());

        return CustomUserDetails.builder().email(user.getEmail())
                .name(user.getName()).id(user.getId()).password(user.getPassword())
                .username(user.getUsername()).roles(roleList)
                .organizations(orgList)
                .simpleGrantedAuthority(getAuthority(roleList)).build();

    }

    private List<SimpleGrantedAuthority> getAuthority(Set<RoleEntity> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getCode())).toList();
    }
}
