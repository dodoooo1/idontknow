package com.idontknow.business.infra.configs.security.auth.providers;

import com.idontknow.business.domain.entities.system.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Transient;
import org.springframework.security.core.authority.AuthorityUtils;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Transient
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    @Serial
    private static final long serialVersionUID = -1137277407288808164L;
    private transient UserInfo userInfo;
    private String token;

    public JwtAuthenticationToken(
            final String token,
            final boolean authenticated,
            final UserInfo userInfo,
            final Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.token = token;
        this.userInfo = userInfo;
        this.setAuthenticated(authenticated);
    }

    public JwtAuthenticationToken(final String token) {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.token = token;
        this.setAuthenticated(false);
    }

    public JwtAuthenticationToken() {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserInfo {
        private Long id;
        private String username;
        private String password;
        private String salt;
        private String phone;
        private String avatar;
        private String nickname;
        private String name;
        private String email;
        private String organization;
        private String createdBy;
        private String updatedBy;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String status;
        private Set<RoleEntity> roles = new HashSet<>();
    }
}
