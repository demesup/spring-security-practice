package com.demesup.security;

import com.demesup.security.model.Role;
import com.demesup.security.model.User;
import com.demesup.security.service.Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringSecurityPracticeApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityPracticeApplication.class, args);
  }

  @Bean
  CommandLineRunner run(Service service) {
    return args -> {
      service.save(new Role(null, "ROLE_USER"));
      service.save(new Role(null, "ROLE_ADMIN"));
      service.save(new Role(null, "ROLE_HAHA"));

      service.save(new User(null, "User", "user", "password", new ArrayList<>()));
      service.save(new User(null, "HAHA", "haha", "password", new ArrayList<>()));

      service.addRole("user", "ROLE_USER");
      service.addRole("haha", "ROLE_HAHA");
      service.addRole("haha", "ROLE_ADMIN");
    };
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
