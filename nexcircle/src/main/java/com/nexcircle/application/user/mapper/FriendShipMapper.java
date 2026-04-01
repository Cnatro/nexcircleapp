package com.nexcircle.application.user.mapper;

import com.nexcircle.application.user.dto.FriendShipRequestDto;
import com.nexcircle.application.user.dto.FriendShipResponseDto;
import com.nexcircle.domain.user.entity.Friendship;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserMapper.class}
)
public interface FriendShipMapper {
//    Friendship toEntity(FriendShipRequestDto dto);
    FriendShipResponseDto toDto(Friendship entity);
}
