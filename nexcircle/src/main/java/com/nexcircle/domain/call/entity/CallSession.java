package com.nexcircle.domain.call.entity;

import com.nexcircle.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "call_sessions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CallSession {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "caller_id", nullable = false)
    private User caller;

    private String type;
    private String status;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
    }
}