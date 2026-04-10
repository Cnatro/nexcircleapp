package com.nexcircle.infrastructure.persistence.notification.mapper;

import com.nexcircle.domain.notification.entity.Notification;
import com.nexcircle.infrastructure.persistence.notification.jpa.NotificationJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NotificationPersistenceMapper {
    Notification toDomain(NotificationJpaEntity jpa);
    NotificationJpaEntity toJpaEntity(Notification notification);
}
