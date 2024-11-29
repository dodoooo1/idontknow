package com.idontknow.business.infra.configs.security.auth.providers;

import com.idontknow.business.application.dto.CustomUserDetails;
import com.idontknow.business.infra.gatewayimpl.dataobject.system.QSysUserDO;
import com.idontknow.business.infra.gatewayimpl.dataobject.system.SysRoleDO;
import com.idontknow.business.infra.gatewayimpl.dataobject.system.SysUserDO;
import com.idontknow.business.infra.gatewayimpl.repositories.SysUserRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Getter
@Component
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
    private final SysUserRepository sysUserRepository;

    @Override
    @Cacheable(value = "userDetails", key = "#username")
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QSysUserDO qUser = QSysUserDO.sysUserDO;
        BooleanExpression expression = qUser.username.eq(username).or(qUser.email.eq(username));
        SysUserDO user = sysUserRepository.findOne(expression).orElseThrow(() -> new RuntimeException("User not found"));
        return CustomUserDetails.builder().email(user.getEmail()).name(user.getName()).id(user.getId()).password(user.getPassword()).username(user.getUsername()).simpleGrantedAuthority(getAuthority(user.getRoles())).build();

    }

    private List<SimpleGrantedAuthority> getAuthority(Set<SysRoleDO> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getCode())).toList();
    }
}
