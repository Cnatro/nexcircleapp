package com.nexcircle.application.messaging.mapper;

import com.nexcircle.application.messaging.dto.ConversationRequest;
import com.nexcircle.application.messaging.dto.ConversationResponse;
import com.nexcircle.application.messaging.dto.UpdateConversation;
import com.nexcircle.domain.messaging.entity.Conversation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.UUID;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ConversationMapper {
    Conversation toEntity(ConversationRequest request);
    ConversationResponse toDto(Conversation conversation);

    Conversation toUpdateMessage(UpdateConversation request, @MappingTarget Conversation conversation);

    default Conversation map(UUID id) {
        if (id == null) return null;

        Conversation c = new Conversation();
        c.setId(id);
        return c;
    }
}
