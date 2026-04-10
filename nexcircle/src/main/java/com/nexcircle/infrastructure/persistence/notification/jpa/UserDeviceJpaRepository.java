package com.nexcircle.infrastructure.persistence.notification.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserDeviceJpaRepository extends JpaRepository<UserDeviceJpaEntity, UUID> {
    Optional<UserDeviceJpaEntity> findByUserId(UUID userId);
}
