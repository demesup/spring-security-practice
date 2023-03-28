package com.demesup.security.service.impl;

import com.demesup.security.exception.NotFoundException;
import com.demesup.security.repository.RoleRepository;
import com.demesup.security.service.Service;
import com.demesup.security.model.Role;
import com.demesup.security.model.User;
import com.demesup.security.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@Slf4j

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ServiceImpl implements Service , UserDetailsService {
  UserRepository userRepository;
  RoleRepository roleRepository;

  @Override
  public User save(User user) {
    log.info("Saving new user {} to the database", user);
    return userRepository.save(user);
  }

  @Override
  public Role save(Role role) {
    log.info("Saving new role {} to the database", role);
    return roleRepository.save(role);
  }

  @Override
  public User addRole(String username, String roleName) {
    log.info("Adding new role {} to the user {}", roleName, username);
    return userRepository.findByUsername(username)
        .map(user -> user.addRole(roleRepository.findRoleByName(roleName)
            .orElseThrow(() -> new NotFoundException(Role.class, roleName))))
        .orElseThrow(() -> new NotFoundException(User.class, username));
  }

  @Override
  public Optional<User> getUser(String username) {
    log.info("Fetching user {}", username);
    return userRepository.findByUsername(username);
  }

  @Override
  public List<User> getUsers() {
    log.info("Fetching all users");
    return userRepository.findAll();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
        .orElseThrow(()-> new NotFoundException(User.class, username));
  }
}
