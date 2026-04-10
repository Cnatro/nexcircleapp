package com.nexcircle.domain.notification.repository;

import com.nexcircle.domain.notification.entity.UserDevice;

import java.util.Optional;
import java.util.UUID;

public interface UserDeviceRepository {
    UserDevice save(UserDevice userDevice);
    Optional<UserDevice> findByUserId(UUID userId);
}
