package com.nexcircle.application.notification.usecase;

import com.nexcircle.domain.notification.entity.UserDevice;
import com.nexcircle.domain.notification.repository.UserDeviceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDeviceQueryService {
    UserDeviceRepository userDeviceRepository;

    public UserDevice findUserDeviceByUserId(UUID userId){
        return  this.userDeviceRepository.findByUserId(userId)
                .orElseThrow(()-> new RuntimeException("Not found user device" + userId));
    }
}
