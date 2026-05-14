package com.nexcircle.infrastructure.persistence.messaging;

import com.nexcircle.domain.messaging.entity.Message;
import com.nexcircle.domain.messaging.repository.MessageRepository;
import com.nexcircle.infrastructure.persistence.messaging.jpa.MessageJpaRepository;
import com.nexcircle.infrastructure.persistence.messaging.mapper.MessagePersistenceMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MessageRepositoryImpl implements MessageRepository {
    MessageJpaRepository messageJpaRepository;
    MessagePersistenceMapper mapper;

    @Override
    public Message save(Message message) {
        var jpa = mapper.toMessageJpaEntity(message);
        return mapper.toMessageDomain(this.messageJpaRepository.save(jpa));
    }

    @Override
    public Page<Message> findAllByConversationId(UUID conversationId, Pageable pageable) {
        return this.messageJpaRepository.findByConversationId(conversationId,pageable)
                .map(this.mapper::toMessageDomain);
    }
}
