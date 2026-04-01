package com.nexcircle.infrastructure.persistence.user.mapper;

import com.nexcircle.domain.user.entity.Friendship;
import com.nexcircle.infrastructure.persistence.user.jpa.FriendshipJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FriendshipPersistenceMapper {
    Friendship toDomain(FriendshipJpaEntity jpaEntity);
    FriendshipJpaEntity toFriendshipJpaEntity(Friendship friendship);
}
