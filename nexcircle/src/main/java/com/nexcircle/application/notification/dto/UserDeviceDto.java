package com.nexcircle.application.notification.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDeviceDto {
    String deviceToken;
    String platform; // android, ios, web
}
