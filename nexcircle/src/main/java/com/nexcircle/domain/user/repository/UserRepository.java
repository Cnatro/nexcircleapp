package com.nexcircle.domain.user.repository;

import com.nexcircle.domain.user.entity.User;

public interface UserRepository {
    User save(User user);
    User findByUsername(String username);
}
