package com.example.dddpattern.application.port.out;

import com.example.dddpattern.domain.entity.User;

import java.util.Optional;

public interface UserRepositoryProvider {
    User save(User user);
    Optional<User> findByUsername(String username);
}
