package com.nexcircle.infrastructure.persistence.user.mapper;

import com.nexcircle.domain.user.entity.User;
import com.nexcircle.infrastructure.persistence.user.jpa.UserJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {
    User toUserDomain(UserJpaEntity jpa);
    UserJpaEntity toUserJpaEntity(User domain);
}
