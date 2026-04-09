package com.nexcircle.application.notification.dto;

import com.nexcircle.shared.enums.MessageType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class NotificationFirebaseDto {
    UUID userId;
    String title;
    String body;
    Map<String,String> data;
    MessageType type;
}
