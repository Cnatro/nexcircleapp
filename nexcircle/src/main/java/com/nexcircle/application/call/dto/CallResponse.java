package com.nexcircle.application.call.dto;

import com.nexcircle.domain.call.enums.CallType;
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
    UUID sessionId;
    UUID callerId;
    String callerName;
    UUID receiverId;
    String receiverName;
    CallType type;
    String status;
    LocalDateTime startedAt;
    LocalDateTime endedAt;
    LocalDateTime createdAt;
//    // optional (WebRTC)
//    String roomId;
//    String token;
}