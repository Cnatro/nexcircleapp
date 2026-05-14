package com.nexcircle.infrastructure.persistence.messaging;

import com.nexcircle.domain.messaging.entity.MessageReceipt;
import com.nexcircle.domain.messaging.repository.MessageReceiptRepository;
import com.nexcircle.infrastructure.persistence.messaging.jpa.MessageReceiptJpaRepository;
import com.nexcircle.infrastructure.persistence.messaging.mapper.MessageReceiptPersistenceMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MessageReceiptRepositoryImpl implements MessageReceiptRepository {
    MessageReceiptJpaRepository jpaRepository;
    MessageReceiptPersistenceMapper persistenceMapper;


    @Override
    public List<MessageReceipt> findByMessageIds(List<UUID> uuids) {
        return this.jpaRepository.findByMessageIdIn(uuids)
                .stream()
                .map(this.persistenceMapper::toDomain)
                .toList();
    }
}
