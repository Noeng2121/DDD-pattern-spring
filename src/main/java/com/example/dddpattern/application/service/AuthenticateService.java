package com.example.dddpattern.application.service;

import com.example.dddpattern.application.port.in.AuthenticateUseCase;
import com.example.dddpattern.application.port.out.UserRepositoryProvider;
import com.example.dddpattern.domain.entity.User;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticateService implements AuthenticateUseCase {


    private final UserRepositoryProvider userRepositoryProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticateService authenticateService;

    public AuthenticateService(UserRepositoryProvider userRepositoryProvider, PasswordEncoder passwordEncoder, AuthenticateService authenticateService) {
        this.userRepositoryProvider = userRepositoryProvider;
        this.passwordEncoder = passwordEncoder;
        this.authenticateService = authenticateService;
    }

    public boolean  login(String username, String password) {
        return authenticateService.login(username, password);
    }
    @Retryable(
            retryFor  = RuntimeException.class,
            maxAttempts = 2,
            backoff = @Backoff(delay = 3000, multiplier = 2)
    )
    @Override
    public boolean authenticatorUser(String username, String password) {
        Optional<User> user = userRepositoryProvider.findByUsername(username);
        if(user.isEmpty()) {
            throw new RuntimeException("User login failed.");
        }
        return passwordEncoder.matches(password, user.get().getPassword());
    }
    @Recover
    public boolean recover(RuntimeException e, String username, String password) {
        System.out.println("Retries exhausted. Login failed for user: " + username);
        return false;
    }

}
