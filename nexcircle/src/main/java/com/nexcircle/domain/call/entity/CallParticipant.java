package com.nexcircle.domain.call.entity;

import com.nexcircle.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "call_participants")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CallParticipant {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "call_id", nullable = false)
    private CallSession callSession;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime joinedAt;
    private LocalDateTime leftAt;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
    }
}