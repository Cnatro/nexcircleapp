package com.nexcircle.application.notification.usecase;

import com.nexcircle.application.notification.dto.UserDeviceDto;
import com.nexcircle.domain.notification.entity.UserDevice;
import com.nexcircle.domain.notification.repository.UserDeviceRepository;
import com.nexcircle.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDeviceService {
    UserDeviceRepository userDeviceRepository;

    public void createUserDevice(UUID userId, UserDeviceDto dto){
        UserDevice userDevice = UserDevice
                .builder()
                .deviceToken(dto.getDeviceToken())
                .user(User.builder().id(userId).build())
                .platform(dto.getPlatform())
                .build();

        this.userDeviceRepository.save(userDevice);
    }
}
