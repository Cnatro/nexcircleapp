package com.nexcircle.application.call.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CallResponse {
    UUID id;
    String callerName;
    String type;
    String status;
    LocalDateTime startedAt;
}