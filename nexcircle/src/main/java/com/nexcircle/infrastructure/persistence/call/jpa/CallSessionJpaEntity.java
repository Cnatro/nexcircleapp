package com.nexcircle.infrastructure.persistence.call.jpa;

import com.nexcircle.infrastructure.persistence.user.jpa.UserJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "call_sessions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CallSessionJpaEntity {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caller_id", nullable = false)
    private UserJpaEntity caller;

    private String type;
    private String status;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
    }
}