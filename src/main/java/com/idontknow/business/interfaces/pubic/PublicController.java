package com.idontknow.business.interfaces.pubic;

import com.idontknow.business.application.services.WebhookSiteService;
import com.idontknow.business.constants.AppUrls;
import com.idontknow.business.infra.configs.security.KeycloakSecurityUtil;
import com.idontknow.business.infra.event.publishers.EventPublisher;
import com.idontknow.business.infra.keycloakclient.Role;
import com.idontknow.business.infra.keycloakclient.User;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.common.util.CollectionUtil;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(PublicController.BASE_URL)
@RequiredArgsConstructor
public class PublicController {
  public static final String BASE_URL = AppUrls.PUBLIC;

  private final EventPublisher eventPublisher;
  private final WebhookSiteService webhookSiteService;

  @Value("${rabbitmq.publishers.webhook.exchange}")
  private String exchange;

  @Value("${rabbitmq.publishers.webhook.routingkey}")
  private String routingKey;

  @GetMapping("/hello-world")
  @ResponseStatus(HttpStatus.OK)
  public String helloWorld() {

    return "Hello world";
  }

  @GetMapping("/publish")
  @ResponseStatus(HttpStatus.OK)
  public String publish() {
    this.eventPublisher.publish(this.exchange, this.routingKey, Map.of("test", "test"));
    return "published";
  }

  @GetMapping("/call-external-api")
  @ResponseStatus(HttpStatus.OK)
  public Mono<String> callExternalAPI() {
    return this.webhookSiteService.post(Map.of());
  }

  @GetMapping("/call-external-api-with-cb")
  @ResponseStatus(HttpStatus.OK)
  public Mono<String> callExternalAPIWithCircuitBreaker() {
    return this.webhookSiteService.postWithCircuitBreaker(Map.of());
  }




  @Autowired
  KeycloakSecurityUtil keycloakUtil;

  @Value("${keycloak.realm}")
  private String realm;

  @GetMapping(value = "/roles")
  public List<Role> getRoles() {
    Keycloak keycloak = keycloakUtil.getKeycloakInstance();
    List<RoleRepresentation> roleRepresentations =
            keycloak.realm(realm).roles().list();
    return mapRoles(roleRepresentations);
  }

  @GetMapping(value = "/roles/{roleName}")
  public Role getRole(@PathVariable("roleName") String roleName) {
    Keycloak keycloak = keycloakUtil.getKeycloakInstance();
    return mapRole(keycloak.realm(realm).roles().get(roleName).toRepresentation());
  }

  @PostMapping(value = "/role")
  public Response createRole(Role role) {
    RoleRepresentation roleRep = mapRoleRep(role);
    Keycloak keycloak = keycloakUtil.getKeycloakInstance();
    keycloak.realm(realm).roles().create(roleRep);
    return Response.ok(role).build();
  }

  @PutMapping(value = "/role")
  public Response updateRole(Role role) {
    RoleRepresentation roleRep = mapRoleRep(role);
    Keycloak keycloak = keycloakUtil.getKeycloakInstance();
    keycloak.realm(realm).roles().get(role.getName()).update(roleRep);
    return Response.ok(role).build();
  }

  @DeleteMapping(value = "/roles/{roleName}")
  public Response deleteUserR(@PathVariable("roleName") String roleName) {
    Keycloak keycloak = keycloakUtil.getKeycloakInstance();
    keycloak.realm(realm).roles().deleteRole(roleName);
    return Response.ok().build();
  }

  public static List<Role> mapRoles(List<RoleRepresentation> representations) {
    List<Role> roles = new ArrayList<>();
    if(CollectionUtil.isNotEmpty(representations)) {
      representations.forEach(roleRep -> roles.add(mapRole(roleRep)));
    }
    return roles;
  }

  public static Role mapRole(RoleRepresentation roleRep) {
    Role role = new Role();
    role.setId(roleRep.getId());
    role.setName(roleRep.getName());
    return role;
  }

  public RoleRepresentation mapRoleRep(Role role) {
    RoleRepresentation roleRep = new RoleRepresentation();
    roleRep.setName(role.getName());
    return roleRep;
  }



  @GetMapping
  @RequestMapping("/users")
  public List<User> getUsers() {
    Keycloak keycloak = keycloakUtil.getKeycloakInstance();
    List<UserRepresentation> userRepresentations =
            keycloak.realm(realm).users().list();
    return mapUsers(userRepresentations);
  }

  @GetMapping(value = "/users/{id}")
  public User getUser(@PathVariable("id") String id) {
    Keycloak keycloak = keycloakUtil.getKeycloakInstance();
    return mapUser(keycloak.realm(realm).users().get(id).toRepresentation());
  }

  @PostMapping(value = "/user")
  public Response createUser(User user) {
    UserRepresentation userRep = mapUserRep(user);
    Keycloak keycloak = keycloakUtil.getKeycloakInstance();
    Response res = keycloak.realm(realm).users().create(userRep);
    return Response.ok(user).build();
  }

  @PutMapping(value = "/user")
  public Response updateUser(User user) {
    UserRepresentation userRep = mapUserRep(user);
    Keycloak keycloak = keycloakUtil.getKeycloakInstance();
    keycloak.realm(realm).users().get(user.getId()).update(userRep);
    return Response.ok(user).build();
  }

  @DeleteMapping(value = "/users/{id}")
  public Response deleteUser(@PathVariable("id") String id) {
    Keycloak keycloak = keycloakUtil.getKeycloakInstance();
    keycloak.realm(realm).users().delete(id);
    return Response.ok().build();
  }

  @GetMapping(value = "/users/{id}/roles")
  public List<Role> getRoles(@PathVariable("id") String id) {
    Keycloak keycloak = keycloakUtil.getKeycloakInstance();
    return mapRoles(keycloak.realm(realm).users()
            .get(id).roles().realmLevel().listAll());
  }

  @PostMapping(value = "/users/{id}/roles/{roleName}")
  public Response createRole(@PathVariable("id") String id,
                             @PathVariable("roleName") String roleName) {
    Keycloak keycloak = keycloakUtil.getKeycloakInstance();
    RoleRepresentation role = keycloak.realm(realm).roles().get(roleName).toRepresentation();
    keycloak.realm(realm).users().get(id).roles().realmLevel().add(Arrays.asList(role));
    return Response.ok().build();
  }

  private List<User> mapUsers(List<UserRepresentation> userRepresentations) {
    List<User> users = new ArrayList<>();
    if(CollectionUtil.isNotEmpty(userRepresentations)) {
      userRepresentations.forEach(userRep -> {
        users.add(mapUser(userRep));
      });
    }
    return users;
  }

  private User mapUser(UserRepresentation userRep) {
    User user = new User();
    user.setId(userRep.getId());
    user.setFirstName(userRep.getFirstName());
    user.setLastName(userRep.getLastName());
    user.setEmail(userRep.getEmail());
    user.setUserName(userRep.getUsername());
    return user;
  }

  private UserRepresentation mapUserRep(User user) {
    UserRepresentation userRep = new UserRepresentation();
    userRep.setId(user.getId());
    userRep.setUsername(user.getUserName());
    userRep.setFirstName(user.getFirstName());
    userRep.setLastName(user.getLastName());
    userRep.setEmail(user.getEmail());
    userRep.setEnabled(true);
    userRep.setEmailVerified(true);
    List<CredentialRepresentation> creds = new ArrayList<>();
    CredentialRepresentation cred = new CredentialRepresentation();
    cred.setTemporary(false);
    cred.setValue(user.getPassword());
    creds.add(cred);
    userRep.setCredentials(creds);
    return userRep;
  }

}
