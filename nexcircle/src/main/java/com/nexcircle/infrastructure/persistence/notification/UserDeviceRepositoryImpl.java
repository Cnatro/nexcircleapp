package com.nexcircle.infrastructure.persistence.notification;

import com.nexcircle.domain.notification.entity.UserDevice;
import com.nexcircle.domain.notification.repository.UserDeviceRepository;
import com.nexcircle.infrastructure.persistence.notification.jpa.UserDeviceJpaEntity;
import com.nexcircle.infrastructure.persistence.notification.jpa.UserDeviceJpaRepository;
import com.nexcircle.infrastructure.persistence.notification.mapper.UserDevicePersistenceMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserDeviceRepositoryImpl implements UserDeviceRepository {
    UserDeviceJpaRepository jpaRepository;
    UserDevicePersistenceMapper persistenceMapper;


    @Override
    public UserDevice save(UserDevice userDevice) {
        UserDeviceJpaEntity jpa = this.persistenceMapper.toJpaEntity(userDevice);

        return this.persistenceMapper.toDomain(this.jpaRepository.save(jpa));
    }

    @Override
    public Optional<UserDevice> findByUserId(UUID userId) {
        return this.jpaRepository.findByUserId(userId).map(this.persistenceMapper::toDomain);
    }
}
