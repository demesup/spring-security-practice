package com.demesup.security.api;

import com.demesup.security.api.request.RoleToUserRequest;
import com.demesup.security.model.Role;
import com.demesup.security.model.User;
import com.demesup.security.service.Service;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Controller {
  Service service;

  @GetMapping("/users")
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.ok().body(service.getUsers());
  }

  @PostMapping("/users/save")
  public ResponseEntity<User> saveUser(@RequestBody User user) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save").toUriString());
    return ResponseEntity.created(uri).body(service.save(user));
  }

  @PostMapping("/roles/save")
  public ResponseEntity<Role> saveRole(@RequestBody Role role) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles/save").toUriString());
    return ResponseEntity.created(uri).body(service.save(role));
  }

  @PostMapping("/roletouser/save")
  public ResponseEntity<User> saveRoleToUser(@RequestBody RoleToUserRequest request) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles/save").toUriString());
    return ResponseEntity.created(uri).body(service.addRole(request.getUsername(), request.getRoleName()));
  }

}
