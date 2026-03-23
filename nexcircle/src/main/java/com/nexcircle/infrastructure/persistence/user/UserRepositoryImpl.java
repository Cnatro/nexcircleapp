package com.nexcircle.infrastructure.persistence.user;

import com.nexcircle.domain.user.entity.User;
import com.nexcircle.domain.user.repository.UserRepository;
import com.nexcircle.infrastructure.persistence.user.jpa.UserJpaRepository;
import com.nexcircle.infrastructure.persistence.user.mapper.UserPersistenceMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRepositoryImpl implements UserRepository {
    UserJpaRepository userJpaRepository;
    UserPersistenceMapper userMapper;

    @Override
    public User save(User user) {
        var jpa = userMapper.toUserJpaEntity(user);
        var saved = userJpaRepository.save(jpa);
        return userMapper.toUserDomain(saved);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userJpaRepository.findByUsername(username).map(userMapper::toUserDomain);
    }
}
