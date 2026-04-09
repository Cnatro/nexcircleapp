package com.nexcircle.application.user.usecase;

import com.nexcircle.application.user.dto.StatusFriendRequestDto;
import com.nexcircle.application.user.mapper.FriendShipMapper;
import com.nexcircle.application.user.mapper.UserMapper;
import com.nexcircle.domain.user.entity.Friendship;
import com.nexcircle.domain.user.repository.FriendRequestRepository;
import com.nexcircle.domain.user.repository.FriendshipRepository;
import com.nexcircle.infrastructure.persistence.user.projection.FriendRequestInfoProjection;
import com.nexcircle.shared.enums.MessageCode;
import com.nexcircle.shared.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class HandleFriendRequestUseCase {
    FriendshipRepository friendshipRepository;
    FriendShipMapper shipMapper;
    UserMapper userMapper;
    FriendRequestRepository friendRequestRepository;

    public void acceptFriendRequest(StatusFriendRequestDto request){
        if (!"accepted".equals(request.getStatus())) {
            throw new AppException(MessageCode.INVALID_FRIEND_REQUEST_STATUS);
        }

        FriendRequestInfoProjection infoProjection = this.friendRequestRepository.updateStatusAndReturn(request.getId(), request.getStatus());

        if (infoProjection == null) {
            throw new AppException(MessageCode.FRIEND_REQUEST_NOT_FOUND);
        }

        if(this.friendshipRepository.isExistsFriendship(infoProjection.getSenderId(),infoProjection.getReceiverId())){
            throw new AppException(MessageCode.FRIENDSHIP_ALREADY_EXISTS);
        }

        Friendship friendship = new Friendship();
        friendship.setUser1(this.userMapper.map(infoProjection.getSenderId()));
        friendship.setUser2(this.userMapper.map(infoProjection.getReceiverId()));
        friendship.setStatus("active");

        this.friendshipRepository.save(friendship);
    }

    public void declinedFriendRequest(StatusFriendRequestDto request){
        if (!"declined".equals(request.getStatus())) {
            throw new AppException(MessageCode.INVALID_FRIEND_REQUEST_STATUS);
        }

        FriendRequestInfoProjection infoProjection = this.friendRequestRepository.updateStatusAndReturn(request.getId(), request.getStatus());
    }
}
