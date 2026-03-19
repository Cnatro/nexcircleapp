package com.nexcircle.infrastructure.security.user;

import com.nexcircle.application.user.mapper.UserMapper;
import com.nexcircle.domain.user.entity.User;
import com.nexcircle.domain.user.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.userRepository.findByUsername(username);
        if(u.getId() == null){
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        return new com.nexcircle.infrastructure.security.user.UserDetails(u);
    }
}
