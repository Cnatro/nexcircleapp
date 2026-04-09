package com.nexcircle.application.messaging.mapper;

import com.nexcircle.application.messaging.dto.MessageResponse;
import com.nexcircle.application.messaging.dto.MessageView;
import com.nexcircle.application.messaging.dto.SendMessRequest;
import com.nexcircle.application.user.mapper.UserMapper;
import com.nexcircle.domain.messaging.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.UUID;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {ConversationMapper.class, UserMapper.class}
)
public interface MessageMapper {
    @Mapping(source = "senderId", target = "sender")
    @Mapping(source = "conversationId", target = "conversation")
    Message toEntity(SendMessRequest request);

    @Mapping(source = "sender.id", target = "senderId")
    MessageResponse toDto(Message message);

    MessageView toMessageView(Message message);

    default Message map(UUID id) {
        if (id == null) return null;

        Message m = new Message();
        m.setId(id);
        return m;
    }
}
