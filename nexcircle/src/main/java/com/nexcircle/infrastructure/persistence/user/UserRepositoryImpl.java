package com.nexcircle.infrastructure.persistence.user;

import com.nexcircle.domain.user.entity.User;
import com.nexcircle.domain.user.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRepositoryImpl implements UserRepository {
    UserJpaRepository userJpaRepository;

    @Override
    public User save(User user) {
        return this.userJpaRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return this.userJpaRepository.findByUsername(username);
    }
}
