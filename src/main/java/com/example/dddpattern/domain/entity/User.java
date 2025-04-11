package com.example.dddpattern.domain.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class User {
    private Integer id;
    private String name;
    private String password;
}
