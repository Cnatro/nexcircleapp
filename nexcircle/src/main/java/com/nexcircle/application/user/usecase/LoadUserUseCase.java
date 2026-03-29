package com.nexcircle.application.user.usecase;

import com.nexcircle.application.messaging.dto.SendMessRequest;
import com.nexcircle.application.user.dto.UserResponse;
import com.nexcircle.application.user.mapper.UserMapper;
import com.nexcircle.domain.user.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class LoadUserUseCase {
    UserRepository userRepository;
    UserMapper userMapper;

    public UserResponse getUserReceiptMessage(SendMessRequest request) {
        return this.userMapper.toDto(
                this.userRepository.findUserReceiptMessageByConversationIdAndUserId(
                        request.getConversationId(), request.getSenderId()
                )
        );
    }
}
