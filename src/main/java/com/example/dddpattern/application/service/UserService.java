package com.example.dddpattern.application.service;

import com.example.dddpattern.application.port.in.UserUseCase;
import com.example.dddpattern.application.port.out.UserRepositoryProvider;
import com.example.dddpattern.domain.entity.User;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserUseCase {

    private final UserRepositoryProvider userRepositoryProvider;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepositoryProvider userRepositoryProvider, PasswordEncoder passwordEncoder) {
        this.userRepositoryProvider = userRepositoryProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(User user) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setName(user.getName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepositoryProvider.save(newUser);
    }

    @Override
    public User update(int id, User user) {
        return null;
    }

    @Override
    public User delete(int id) {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }


}
