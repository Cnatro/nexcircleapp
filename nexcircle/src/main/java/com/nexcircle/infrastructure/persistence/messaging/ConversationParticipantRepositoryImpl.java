package com.nexcircle.infrastructure.persistence.messaging;

import com.nexcircle.domain.messaging.entity.ConversationParticipant;
import com.nexcircle.domain.messaging.repository.ConversationParticipantRepository;
import com.nexcircle.infrastructure.persistence.messaging.jpa.ConversationParticipantJpaEntity;
import com.nexcircle.infrastructure.persistence.messaging.jpa.ConversationParticipantJpaRepository;
import com.nexcircle.infrastructure.persistence.messaging.mapper.ConversationParticipantPersistenceMapper;
import com.nexcircle.infrastructure.persistence.messaging.mapper.ConversationPersistenceMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ConversationParticipantRepositoryImpl implements ConversationParticipantRepository {
    ConversationParticipantJpaRepository conversationParticipantJpaRepository;
    ConversationParticipantPersistenceMapper conversationParticipantPersistenceMapper;

    @Override
    public void saveAll(List<ConversationParticipant> conversationParticipantList) {
        List<ConversationParticipantJpaEntity> jpas = conversationParticipantList
                .stream()
                .map(this.conversationParticipantPersistenceMapper::toConversationParticipantJpaEntity)
                .toList();
        this.conversationParticipantJpaRepository.saveAll(jpas);
    }
}
