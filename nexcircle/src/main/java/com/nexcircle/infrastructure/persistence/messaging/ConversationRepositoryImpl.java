package com.nexcircle.infrastructure.persistence.messaging;

import com.nexcircle.domain.messaging.entity.Conversation;
import com.nexcircle.domain.messaging.repository.ConversationRepository;
import com.nexcircle.infrastructure.persistence.messaging.jpa.ConversationJpaEntity;
import com.nexcircle.infrastructure.persistence.messaging.jpa.ConversationJpaRepository;
import com.nexcircle.infrastructure.persistence.messaging.mapper.ConversationPersistenceMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ConversationRepositoryImpl implements ConversationRepository {
    ConversationJpaRepository conversationJpaRepository;
    ConversationPersistenceMapper conversationPersistenceMapper;

    @Override
    public Conversation save(Conversation conversation) {
        ConversationJpaEntity conversationJpa = this.conversationPersistenceMapper
                .toConversationJpaEntity(conversation);
        ConversationJpaEntity conversationSaved = this.conversationJpaRepository.save(conversationJpa);
        return this.conversationPersistenceMapper.toConversationEntity(conversationSaved);
    }
}
