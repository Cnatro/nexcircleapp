package com.nexcircle.infrastructure.persistence.user.mapper;

import com.nexcircle.domain.user.entity.FriendRequest;
import com.nexcircle.infrastructure.persistence.user.jpa.FriendRequestJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FriendRequestPersistenceMapper {
    FriendRequest toUserDomain(FriendRequestJpaEntity jpa);
    FriendRequestJpaEntity toUserJpaEntity(FriendRequest domain);
}
