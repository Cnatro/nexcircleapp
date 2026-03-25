package com.nexcircle.infrastructure.persistence.call.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.UUID;

public interface CallSessionJpaRepository extends JpaRepository<CallSessionJpaEntity, UUID> {
    @Modifying
    @Query("UPDATE CallSessionJpaEntity c SET c.status = :newStatus, c.endedAt = :endedAt " +
            "WHERE c.status = :oldStatus AND c.startedAt < :threshold")
    int updateStatusForTimeoutCalls(String newStatus, String oldStatus, LocalDateTime threshold, LocalDateTime endedAt);
}
