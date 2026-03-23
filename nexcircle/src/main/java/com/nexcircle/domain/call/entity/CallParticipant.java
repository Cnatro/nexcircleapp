package com.nexcircle.domain.call.entity;

import com.nexcircle.domain.user.entity.User;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class CallParticipant {
    private UUID id;
    private CallSession callSession;
    private User user;
    private LocalDateTime joinedAt;
    private LocalDateTime leftAt;
}