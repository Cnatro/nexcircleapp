package com.nexcircle.application.user.usecase;

import com.nexcircle.application.messaging.dto.SendMessRequest;
import com.nexcircle.application.user.dto.UserFilter;
import com.nexcircle.application.user.dto.UserResponse;
import com.nexcircle.application.user.mapper.UserMapper;
import com.nexcircle.domain.user.entity.User;
import com.nexcircle.domain.user.repository.UserRepository;
import com.nexcircle.domain.user.service.SecurityContextService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class GetUserUseCase {
    UserRepository userRepository;
    UserMapper userMapper;
    SecurityContextService securityContextService;

    public UserResponse getUserReceiptMessage(SendMessRequest request) {
        return this.userMapper.toDto(
                this.userRepository.findUserReceiptMessageByConversationIdAndUserId(
                        request.getConversationId(), request.getSenderId()
                )
        );
    }

    public Page<UserResponse> getNearbyUsers(UserFilter filter){
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize(), Sort.by("createdAt").descending());

        return this.userRepository.findNearByUsers(this.securityContextService.getCurrentUserId(), pageable).map(this.userMapper::toDto);
    }

    public UserResponse findUserById(UUID id){
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found user"));
        return this.userMapper.toDto(user);
    }
}
