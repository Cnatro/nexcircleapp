package com.nexcircle.domain.call.entity;

import com.nexcircle.domain.call.enums.CallType;
import com.nexcircle.domain.user.entity.User;
import com.nexcircle.shared.enums.MessageCode;
import com.nexcircle.shared.exception.AppException;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class CallSession {
    private UUID id;
    private User caller;
    private CallType type;
    private String status;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    // Business Logic: Khởi tạo một cuộc gọi mới
    public static CallSession createPending(User caller, CallType type) {
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

    public void reject() {
        validateStatus("PENDING");
        this.status = "REJECTED";
        this.endedAt = LocalDateTime.now();
    }

    public void cancel() {
        validateStatus("PENDING");
        this.status = "CANCELLED";
        this.endedAt = LocalDateTime.now();
    }

    public void end() {
        if (!"ACCEPTED".equals(this.status)) {
            throw new AppException(MessageCode.CALL_FAILED, "Cannot end a call that hasn't started");
        }
        this.status = "ENDED";
        this.endedAt = LocalDateTime.now();
    }

    private void validateStatus(String expected) {
        if (!expected.equals(this.status)) {
            throw new AppException(MessageCode.CALL_FAILED, "Invalid call status transition");
        }
    }


}