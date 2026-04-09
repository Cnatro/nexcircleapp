package com.nexcircle.application.user.usecase;

import com.nexcircle.application.notification.dto.NotificationFirebaseDto;
import com.nexcircle.application.user.dto.FriendRequestDto;
import com.nexcircle.application.user.mapper.FriendRequestMapper;
import com.nexcircle.domain.user.entity.FriendRequest;
import com.nexcircle.domain.user.repository.FriendRequestRepository;
import com.nexcircle.domain.user.service.SecurityContextService;
import com.nexcircle.infrastructure.external.FirebaseService;
import com.nexcircle.shared.enums.MessageType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SendFriendRequestUseCase {
    FriendRequestRepository friendRequestRepository;
    FriendRequestMapper friendRequestMapper;
    SecurityContextService securityContextService;
    FirebaseService firebaseService;

    public void createFrRequest(FriendRequestDto dto) {

        dto.setSenderId(this.securityContextService.getCurrentUserId());
        FriendRequest saved = this.friendRequestRepository.save(this.friendRequestMapper.toEntity(dto));

        if (saved.getId() != null) {
            this.firebaseService.sentNotification(NotificationFirebaseDto
                    .builder()
                    .type(MessageType.FRIEND_REQUEST)
                    .title("Lời mời kết bạn")
                    .body("Bạn có một lời mời kết bạn mới")
                    .data(Map.of(
                            "friendRequestId", saved.getId().toString(),
                            "senderId", dto.getSenderId().toString(),
                            "type", "FRIEND_REQUEST"
                    ))
                    .userId(saved.getReceiver().getId())
                    .build()
            );
        }
    }
}
