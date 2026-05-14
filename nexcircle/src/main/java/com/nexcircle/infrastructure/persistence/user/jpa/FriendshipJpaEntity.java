package com.nexcircle.infrastructure.persistence.user.jpa;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "friendships")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FriendshipJpaEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user1_id", nullable = false)
    private UserJpaEntity user1;

    @ManyToOne
    @JoinColumn(name = "user2_id", nullable = false)
    private UserJpaEntity user2;

    private String status;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
        if (status == null) status = "active";
        createdAt = LocalDateTime.now();
    }
}