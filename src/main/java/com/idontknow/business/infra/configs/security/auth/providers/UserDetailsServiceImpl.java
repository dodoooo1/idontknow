package com.idontknow.business.infra.configs.security.auth.providers;

import com.idontknow.business.application.dto.AuthenticationUser;
import com.idontknow.business.application.services.system.SysUserService;
import com.idontknow.business.domain.entities.system.SysRoleEntity;
import com.idontknow.business.domain.entities.system.SysUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Getter
@Component
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final SysUserService sysUserService;
    public AuthenticationUser loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return AuthenticationUser.builder().email(user.getEmail()).name(user.getName()).id(user.getId()).username(user.getUsername()).simpleGrantedAuthority(getAuthority(user.getRoles())).build();

    }

    private List<SimpleGrantedAuthority> getAuthority(Set<SysRoleEntity> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getCode())).toList();
    }
}
