package com.idontknow.business.infra.configs.security.auth.providers;

import com.idontknow.business.application.dto.AuthenticationUser;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Transient;
import org.springframework.security.core.authority.AuthorityUtils;

import java.io.Serial;
import java.util.Collection;

@Getter
@Transient
public class ApiKeyAuthentication extends AbstractAuthenticationToken {

  @Serial private static final long serialVersionUID = -1137277407288808164L;

  private String token;
  private transient AuthenticationUser authenticationUser;

  public ApiKeyAuthentication(
      final String token,
      final boolean authenticated,
      final AuthenticationUser authenticationUser,
      final Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.token = token;
    this.authenticationUser = authenticationUser;
    this.setAuthenticated(authenticated);
  }

  public ApiKeyAuthentication(final String token) {
    super(AuthorityUtils.NO_AUTHORITIES);
    this.token = token;
    this.setAuthenticated(false);
  }

  public ApiKeyAuthentication() {
    super(AuthorityUtils.NO_AUTHORITIES);
    this.setAuthenticated(false);
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return this.token;
  }

}
