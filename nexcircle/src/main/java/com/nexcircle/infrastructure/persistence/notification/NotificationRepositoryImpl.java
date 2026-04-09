package com.nexcircle.infrastructure.persistence.notification;

import com.nexcircle.domain.notification.entity.Notification;
import com.nexcircle.domain.notification.repository.NotificationRepository;
import com.nexcircle.infrastructure.persistence.notification.jpa.NotificationJpaEntity;
import com.nexcircle.infrastructure.persistence.notification.jpa.NotificationJpaRepository;
import com.nexcircle.infrastructure.persistence.notification.mapper.NotificationPersistenceMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class NotificationRepositoryImpl implements NotificationRepository {
    NotificationJpaRepository jpaRepository;
    NotificationPersistenceMapper persistenceMapper;


    @Override
    public Notification save(Notification notification) {
        NotificationJpaEntity jpa = this.persistenceMapper.toJpaEntity(notification);

        return this.persistenceMapper.toDomain(
                this.jpaRepository.save(jpa)
        );
    }

    @Override
    public Optional<Notification> findById(UUID id) {
        return this.jpaRepository.findById(id).map(this.persistenceMapper::toDomain);
    }

    @Override
    public Page<Notification> findAllByUser(UUID id, Pageable pageable) {
        return this.jpaRepository.findByUserId(id, pageable).map(this.persistenceMapper::toDomain);
    }
}
