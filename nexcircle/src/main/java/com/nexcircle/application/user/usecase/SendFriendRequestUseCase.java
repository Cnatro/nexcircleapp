package com.nexcircle.application.user.usecase;

import com.nexcircle.application.user.dto.FriendRequestDto;
import com.nexcircle.application.user.mapper.FriendRequestMapper;
import com.nexcircle.domain.user.repository.FriendRequestRepository;
import com.nexcircle.domain.user.service.SecurityContextService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SendFriendRequestUseCase {
    FriendRequestRepository friendRequestRepository;
    FriendRequestMapper friendRequestMapper;
    SecurityContextService securityContextService;

    public void createFrRequest(FriendRequestDto dto){

        dto.setSenderId(this.securityContextService.getCurrentUserId());
        this.friendRequestRepository.save(this.friendRequestMapper.toEntity(dto));
    }
}
