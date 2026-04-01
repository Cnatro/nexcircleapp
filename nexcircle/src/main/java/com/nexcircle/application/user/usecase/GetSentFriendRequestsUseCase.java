package com.nexcircle.application.user.usecase;

import com.nexcircle.application.user.dto.FriendRequestFilter;
import com.nexcircle.application.user.dto.FriendRqResponseDto;
import com.nexcircle.application.user.mapper.FriendRequestMapper;
import com.nexcircle.domain.user.entity.FriendRequest;
import com.nexcircle.domain.user.repository.FriendRequestRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class GetSentFriendRequestsUseCase {
    FriendRequestRepository friendRequestRepository;
    FriendRequestMapper friendRequestMapper;

    public Page<FriendRqResponseDto> getFriendRequestsByStatusAndReceiverId(FriendRequestFilter filter){

        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize(), Sort.by("createdAt").descending());
        return this.friendRequestRepository.findAllByStatusAndReceiverId(filter.getStatus(),filter.getReceiverId(), pageable)
                .map(fr -> new FriendRqResponseDto(
                        fr.getId(),
                        fr.getSenderId(),
                        fr.getSenderName(),
                        fr.getSenderAvatar(),
                        fr.getCreatedAt()
                ));
    }
}
