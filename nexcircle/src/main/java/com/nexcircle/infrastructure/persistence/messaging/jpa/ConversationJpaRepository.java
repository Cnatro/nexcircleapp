package com.nexcircle.infrastructure.persistence.messaging.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConversationJpaRepository extends JpaRepository<ConversationJpaEntity, UUID> {
}
