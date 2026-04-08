package com.nexcircle.infrastructure.persistence.messaging.mapper;

import com.nexcircle.domain.messaging.entity.Attachment;
import com.nexcircle.infrastructure.persistence.messaging.jpa.AttachmentJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AttachmentPersistenceMapper {
    Attachment toDomain(AttachmentJpaEntity jpa);
    AttachmentJpaEntity toJpaEntity(Attachment attachment);
}
