package com.nexcircle.infrastructure.persistence.messaging.mapper;

import com.nexcircle.domain.messaging.entity.Conversation;
import com.nexcircle.infrastructure.persistence.messaging.jpa.ConversationJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ConversationPersistenceMapper {
    Conversation toConversationEntity(ConversationJpaEntity jpa);
    ConversationJpaEntity toConversationJpaEntity(Conversation conversation);
}
