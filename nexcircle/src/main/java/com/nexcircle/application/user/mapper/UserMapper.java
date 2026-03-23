package com.nexcircle.application.user.mapper;

import com.nexcircle.application.user.dto.UserRegister;
import com.nexcircle.application.user.dto.UserResponse;
import com.nexcircle.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
//    User toEntity(UserRegister register); //DTO → Domain không nên qua mapper ❌
    UserResponse toDto(User user);
}
//CREATE → factory method 🔥
//UPDATE → method trong domain 🔥
//READ → mapper 🔥
