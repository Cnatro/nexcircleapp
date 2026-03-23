package com.nexcircle.domain.call.entity;

import com.nexcircle.domain.user.entity.User;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class CallSession {
    private UUID id;
    private User caller;
    private String type;
    private String status;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    // Business Logic: Khởi tạo một cuộc gọi mới
    public static CallSession createPending(User caller, String type) {
        return CallSession.builder()
                .id(UUID.randomUUID())
                .caller(caller)
                .type(type)
                .status("PENDING")
                .startedAt(LocalDateTime.now())
                .build();
    }

    // Business Logic: Chấp nhận cuộc gọi
    public void accept() {
        if (!"PENDING".equals(this.status)) {
            throw new IllegalStateException("Only pending calls can be accepted");
        }
        this.status = "ACCEPTED";
    }
}