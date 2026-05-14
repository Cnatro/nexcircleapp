package com.nexcircle.infrastructure.persistence.messaging;

import com.nexcircle.domain.messaging.entity.Attachment;
import com.nexcircle.domain.messaging.repository.AttachmentRepository;
import com.nexcircle.infrastructure.persistence.messaging.jpa.AttachmentJpaRepository;
import com.nexcircle.infrastructure.persistence.messaging.mapper.AttachmentPersistenceMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AttachmentRepositoryImpl implements AttachmentRepository {
    AttachmentJpaRepository jpaRepository;
    AttachmentPersistenceMapper persistenceMapper;


    @Override
    public List<Attachment> findByMessageIds(List<UUID> uuids) {
        return this.jpaRepository.findByMessageIdIn(uuids)
                .stream()
                .map(this.persistenceMapper::toDomain)
                .toList();
    }
}
