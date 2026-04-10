package com.nexcircle.application.user.usecase;

import com.nexcircle.application.notification.dto.NotificationFirebaseDto;
import com.nexcircle.application.user.dto.FriendShipRequestDto;
import com.nexcircle.application.user.dto.FriendShipResponseDto;
import com.nexcircle.application.user.mapper.FriendShipMapper;
import com.nexcircle.domain.user.entity.Friendship;
import com.nexcircle.domain.user.repository.FriendshipRepository;
import com.nexcircle.infrastructure.external.FirebaseService;
import com.nexcircle.shared.enums.MessageCode;
import com.nexcircle.shared.enums.MessageType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class HandleFriendShipUseCase {
    FriendshipRepository friendshipRepository;
    FriendShipMapper friendShipMapper;
    FirebaseService firebaseService;

    public FriendShipResponseDto updateStatusFriendShip(UUID fsID, FriendShipRequestDto request){
        Friendship friendship = this.friendshipRepository.findById(fsID)
                .orElseThrow(()-> new RuntimeException(MessageCode.FRIEND_SHIP_NOT_FOUND.name()));

        friendship.setStatus(request.getStatus());

        Friendship saved  = this.friendshipRepository.save(friendship);

        if (saved.getId() != null) {

            String status = saved.getStatus();

            if ("unfriended".equals(status)) {
                firebaseService.sentNotification(NotificationFirebaseDto.builder()
                        .type(MessageType.FRIEND_UPDATE)
                        .title("Đã hủy kết bạn")
                        .body("Bạn đã bị hủy kết bạn")
                        .data(Map.of(
                                "friendshipId", saved.getId().toString(),
                                "type", "UNFRIENDED"
                        ))
                        .userId(saved.getUser2().getId()) // người còn lại
                        .build()
                );

            } else if ("blocked".equals(status)) {
                firebaseService.sentNotification(NotificationFirebaseDto.builder()
                        .type(MessageType.FRIEND_UPDATE)
                        .title("Bạn đã bị chặn")
                        .body("Bạn không thể liên lạc với người này nữa")
                        .data(Map.of(
                                "friendshipId", saved.getId().toString(),
                                "type", "BLOCKED"
                        ))
                        .userId(saved.getUser2().getId())
                        .build()
                );
            }
        }

        return this.friendShipMapper.toDto(saved);
    }
}
