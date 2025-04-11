package com.example.dddpattern.application.port.in;

public interface AuthenticateUseCase {
    boolean authenticatorUser(String username, String password);

}
