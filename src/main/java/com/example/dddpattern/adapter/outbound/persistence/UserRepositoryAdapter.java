package com.example.dddpattern.adapter.outbound.persistence;

import com.example.dddpattern.application.port.out.UserRepositoryProvider;
import com.example.dddpattern.domain.entity.User;
import com.example.dddpattern.infrastructure.database.entity.UserEntity;
import com.example.dddpattern.infrastructure.database.mapper.JpaUserMapper;
import com.example.dddpattern.infrastructure.database.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryProvider {

    private final JpaUserRepository jpaUserRepository;
    private final JpaUserMapper mapper;

    @Override
    public User save(User user) {
        UserEntity userEntity = mapper.from(user);
        UserEntity saveEntity = jpaUserRepository.save(userEntity);
        return mapper.from(saveEntity);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<UserEntity> user = jpaUserRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return user.map(mapper::from);
    }
}
