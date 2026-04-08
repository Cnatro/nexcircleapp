package com.nexcircle.application.call.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignalMessage {
    String type;        // "OFFER", "ANSWER", "ICE_CANDIDATE"
     String fromUserId;
     UUID toUserId;
     UUID sessionId;
     Object data;        // Chứa thông tin kỹ thuật WebRTC (SDP/ICE)
}
