package com.nexcircle.application.user.usecase;

import com.nexcircle.application.user.dto.UserLogin;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class LoginUserUseCase  {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public Boolean isLogin(UserLogin userLogin){
        User u = this.userRepository.findByUsername(userLogin.getUsername());
        if( u == null) return false;
        return this.passwordEncoder.matches(userLogin.getPassword(), u.getPassword());
    }

}
