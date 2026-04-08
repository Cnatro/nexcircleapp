package com.nexcircle.infrastructure.persistence.messaging.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageReceiptJpaRepository extends JpaRepository<MessageReceiptJpaEntity, UUID> {
    List<MessageReceiptJpaEntity> findByMessageIdIn(List<UUID> messageIds);
}
