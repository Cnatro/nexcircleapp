package com.nexcircle.application.user.usecase;

import com.nexcircle.application.notification.usecase.UserDeviceService;
import com.nexcircle.application.user.dto.UserRegister;
import com.nexcircle.application.user.dto.UserResponse;
import com.nexcircle.application.user.mapper.UserMapper;
import com.nexcircle.domain.user.entity.User;
import com.nexcircle.domain.user.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RegisterUserUseCase {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    UserDeviceService userDeviceService;

    public UserResponse registerUser(UserRegister userRegister){
        log.debug("Register user");
        User u = User.create(
                userRegister.getUsername(),
                userRegister.getEmail(),
                userRegister.getPassword()
        );
        u.encodePassword(this.passwordEncoder.encode(userRegister.getPassword()));
        User saved = this.userRepository.save(u);

        if (userRegister.getUserDeviceDto() != null){
            this.userDeviceService.createUserDevice(saved.getId(),userRegister.getUserDeviceDto());
        }

        return this.userMapper.toDto(saved);
    }
}
