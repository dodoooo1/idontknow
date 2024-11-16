package com.idontknow.business.infra.configs.security.auth.providers;

import com.idontknow.business.application.dto.AuthenticationUser;
import com.idontknow.business.application.services.system.SysUserService;
import com.idontknow.business.application.services.system.dto.SysRoleResponse;
import com.idontknow.business.application.services.system.dto.SysUserResponse;
import com.idontknow.business.domain.entities.system.SysRole;
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
        SysUserResponse user = sysUserService.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return AuthenticationUser.builder().email(user.email()).name(user.name()).id(user.id()).username(user.username()).simpleGrantedAuthority(getAuthority(user.roles())).build();

    }

    private List<SimpleGrantedAuthority> getAuthority(Set<SysRoleResponse> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.code())).toList();
    }
}
