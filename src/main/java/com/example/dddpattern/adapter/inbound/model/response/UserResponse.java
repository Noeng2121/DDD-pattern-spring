package com.example.dddpattern.adapter.inbound.model.response;

import lombok.Data;

@Data
public class UserResponse {
    private Integer id;
    private String name;
    private String password;
}
