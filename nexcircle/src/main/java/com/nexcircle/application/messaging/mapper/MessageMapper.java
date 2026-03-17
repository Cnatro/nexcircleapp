package com.nexcircle.application.messaging.mapper;

import com.nexcircle.application.messaging.dto.MessageResponse;
import com.nexcircle.application.messaging.dto.SendMessRequest;
import com.nexcircle.domain.messaging.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MessageMapper {
    Message toEntity(SendMessRequest request);
    MessageResponse toDto(Message message);
}
