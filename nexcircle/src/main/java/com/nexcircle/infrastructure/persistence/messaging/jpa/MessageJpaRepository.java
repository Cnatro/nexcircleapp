package com.nexcircle.infrastructure.persistence.messaging.jpa;

import com.nexcircle.domain.messaging.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageJpaRepository extends JpaRepository<MessageJpaEntity, UUID> {
}
