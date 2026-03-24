package com.nexcircle.domain.messaging.repository;

import com.nexcircle.domain.messaging.entity.Conversation;

public interface ConversationRepository {
    Conversation save(Conversation conversation);
}
