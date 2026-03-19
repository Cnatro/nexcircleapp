package com.nexcircle.application.user.usecase;

import com.nexcircle.application.user.dto.UserResponse;
import com.nexcircle.application.user.mapper.UserMapper;
import com.nexcircle.domain.user.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class GetCurrentUserUseCase {
    UserRepository userRepository;
    UserMapper userMapper;

    public UserResponse getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return this.userMapper.toDto(this.userRepository.findByUsername(username));
    }
}
