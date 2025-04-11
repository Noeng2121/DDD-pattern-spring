package com.example.dddpattern.application.port.in;

import com.example.dddpattern.domain.entity.User;

public interface UserUseCase {

    User create(User user);
    User update(int id, User user);
    User delete(int id);
    User findById(int id);
}
