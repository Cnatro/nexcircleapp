package com.nexcircle.application.call.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignalMessage {
    String type;        // "OFFER", "ANSWER", "ICE_CANDIDATE"
     String fromUserId;
     String toUserId;
     Object data;        // Chứa thông tin kỹ thuật WebRTC (SDP/ICE)
}
