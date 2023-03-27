package com.demesup.security.service;

import com.demesup.security.model.Role;
import com.demesup.security.model.User;

import java.util.List;
import java.util.Optional;

public interface Service {
  User save(User user);

  Role save(Role user);

  User addRole(String username, String roleName);

  Optional<User> getUser(String username);

  List<User> getUsers();


}
