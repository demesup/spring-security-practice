package com.demesup.security.auth;

import com.demesup.security.config.JwtService;
import com.demesup.security.user.Role;
import com.demesup.security.user.User;
import com.demesup.security.user.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
  UserRepository repository;
  PasswordEncoder passwordEncoder;
  JwtService jwtService;
  AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegistrationRequest request) {
    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();
    repository.save(user);
    var token = jwtService.generateToken(user);
    return AuthenticationResponse.builder().token(token).build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var token = jwtService.generateToken(user);
    return AuthenticationResponse.builder().token(token).build();
  }
}
