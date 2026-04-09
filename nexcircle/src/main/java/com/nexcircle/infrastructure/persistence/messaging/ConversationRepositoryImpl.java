package com.nexcircle.infrastructure.persistence.messaging;

import com.nexcircle.domain.messaging.entity.Conversation;
import com.nexcircle.domain.messaging.repository.ConversationRepository;
import com.nexcircle.infrastructure.persistence.messaging.jpa.ConversationJpaEntity;
import com.nexcircle.infrastructure.persistence.messaging.jpa.ConversationJpaRepository;
import com.nexcircle.infrastructure.persistence.messaging.mapper.ConversationPersistenceMapper;
import com.nexcircle.infrastructure.persistence.messaging.projection.ConversationListItemProjection;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public List<ConversationListItemProjection> findAllConversationWithUserLogin(UUID userId) {
        return this.conversationJpaRepository.findAllConversationWithUserLogin(userId);
    }

    @Override
    public Optional<Conversation> findExactConversation(List<UUID> userIds, long szie, String type) {

        return this.conversationJpaRepository
                .findExactConversation(userIds,szie, type)
                .map(this.conversationPersistenceMapper::toConversationEntity);
    }

    @Override
    public Optional<Conversation> findById(UUID id) {
        return this.conversationJpaRepository.findById(id).map(this.conversationPersistenceMapper::toConversationEntity);
    }
}
