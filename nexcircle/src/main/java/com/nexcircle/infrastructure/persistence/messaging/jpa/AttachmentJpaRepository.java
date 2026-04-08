package com.nexcircle.infrastructure.persistence.messaging.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AttachmentJpaRepository extends JpaRepository<AttachmentJpaEntity, UUID> {
    List<AttachmentJpaEntity> findByMessageIdIn(List<UUID> messageIds);
}
