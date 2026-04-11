package com.nexcircle.domain.messaging.repository;

import com.nexcircle.domain.messaging.entity.Conversation;
import com.nexcircle.infrastructure.persistence.messaging.projection.ConversationListItemProjection;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConversationRepository {
    Conversation save(Conversation conversation);
    List<ConversationListItemProjection> findAllConversationWithUserLogin(UUID userId);
    Optional<Conversation> findExactConversation(List<UUID> userIds, long szie, String type);
    Optional<Conversation> findById(UUID id);
    List<ConversationListItemProjection> findConversationDetail(UUID conversationId);
    Conversation updateLastMessage(UUID conversationId, UUID lastmessageId);
}
