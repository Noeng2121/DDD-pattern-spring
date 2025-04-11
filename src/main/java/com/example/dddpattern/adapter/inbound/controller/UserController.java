package com.example.dddpattern.adapter.inbound.controller;

import com.example.dddpattern._share.paging.HttpResponseEntity;
import com.example.dddpattern.adapter.inbound.mapper.UserHttpMapper;
import com.example.dddpattern.adapter.inbound.model.request.UserRequest;
import com.example.dddpattern.adapter.inbound.model.response.UserResponse;
import com.example.dddpattern.application.port.in.UserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserHttpMapper mapper;
    private final UserUseCase userUseCase;



    public UserController(UserHttpMapper mapper, UserUseCase userUseCase) {
        this.mapper = mapper;
        this.userUseCase = userUseCase;
    }

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest request) {
        UserResponse response = Optional.ofNullable(request)
                .map(mapper::from)
                .map(userUseCase::create)
                .map(mapper::from)
                .orElse(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public HttpResponseEntity<UserResponse> getByClientId(
            @PathVariable("id") int userId) {
        return HttpResponseEntity.<UserResponse>builder()
                .data(mapper.from(userUseCase.findById(userId)))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        userUseCase.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }



}
