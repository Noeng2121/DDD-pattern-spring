package com.example.dddpattern.adapter.inbound.model.response;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class TokenResponse {
    private String message;
    private String token;
    public TokenResponse(String message, String token) {
        this.token = token;
        this.message = message;
    }
}
