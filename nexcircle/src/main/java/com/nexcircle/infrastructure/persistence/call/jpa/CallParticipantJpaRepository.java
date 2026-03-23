package com.nexcircle.infrastructure.persistence.call.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CallParticipantJpaRepository extends JpaRepository<CallParticipantJpaEntity, UUID> {
}
