package com.example.dddpattern.adapter.inbound.controller;

import com.example.dddpattern._share.generator.JwtService;
import com.example.dddpattern.adapter.inbound.model.request.UserRequest;
import com.example.dddpattern.adapter.inbound.model.response.TokenResponse;
import com.example.dddpattern.application.port.in.AuthenticateUseCase;
import com.example.dddpattern.application.service.AuthenticateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/api/v1")
public class AuthenticateController {

    private final AuthenticateUseCase authenticateUseCase;
    private final JwtService jwtService;

    public AuthenticateController(AuthenticateUseCase authenticateUseCase, JwtService jwtService) {
        this.authenticateUseCase = authenticateUseCase;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest request) {
        boolean isAuthenticated = authenticateUseCase.authenticatorUser(request.getUsername(), request.getPassword());

        if (!isAuthenticated) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }
        String token = jwtService.generateToken(request.getUsername());
        return ResponseEntity.ok(new TokenResponse("successfully", token));
    }

}
