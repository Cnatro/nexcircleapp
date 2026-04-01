package com.nexcircle.application.user.mapper;

import com.nexcircle.application.user.dto.FriendRequestDto;
import com.nexcircle.application.user.dto.FriendRqResponseDto;
import com.nexcircle.domain.user.entity.FriendRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserMapper.class}
)
public interface FriendRequestMapper {
    @Mapping(source = "senderId", target = "sender")
    @Mapping(source = "receiverId", target = "receiver")
    FriendRequest toEntity(FriendRequestDto friendRequestDto);

    FriendRqResponseDto toDto(FriendRequest friendRequest);
}
