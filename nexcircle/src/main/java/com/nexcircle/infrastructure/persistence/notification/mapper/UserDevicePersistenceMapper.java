package com.nexcircle.infrastructure.persistence.notification.mapper;

import com.nexcircle.domain.notification.entity.UserDevice;
import com.nexcircle.infrastructure.persistence.notification.jpa.UserDeviceJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserDevicePersistenceMapper {
    UserDevice toDomain(UserDeviceJpaEntity jpa);
    UserDeviceJpaEntity toJpaEntity(UserDevice userDevice);
}
