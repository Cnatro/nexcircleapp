package com.nexcircle.domain.messaging.repository;

import com.nexcircle.domain.messaging.entity.ConversationParticipant;

import java.util.List;

public interface ConversationParticipantRepository {
    void saveAll(List<ConversationParticipant> conversationParticipantList);
}
