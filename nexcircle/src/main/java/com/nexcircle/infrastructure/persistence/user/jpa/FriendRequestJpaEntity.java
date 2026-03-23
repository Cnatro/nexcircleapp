package com.nexcircle.infrastructure.persistence.user.jpa;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "friend_requests")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FriendRequestJpaEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private UserJpaEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private UserJpaEntity receiver;

    private String status;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
        if (status == null) status = "pending";
        createdAt = LocalDateTime.now();
    }
}