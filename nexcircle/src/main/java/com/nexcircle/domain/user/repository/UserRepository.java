package com.nexcircle.domain.user.repository;

import com.nexcircle.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByUsername(String username);
}
