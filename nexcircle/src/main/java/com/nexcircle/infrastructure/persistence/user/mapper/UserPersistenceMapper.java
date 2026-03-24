package com.nexcircle.infrastructure.persistence.user.mapper;

import com.nexcircle.domain.user.entity.User;
import com.nexcircle.infrastructure.persistence.user.jpa.UserJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserPersistenceMapper {
    User toUserDomain(UserJpaEntity jpa);
    UserJpaEntity toUserJpaEntity(User domain);
}
