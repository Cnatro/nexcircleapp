package com.nexcircle.infrastructure.persistence.notification.jpa;

import com.nexcircle.infrastructure.persistence.user.jpa.UserJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notifications")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NotificationJpaEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    private String type;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Boolean isRead;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
        if (isRead == null) isRead = false;
        createdAt = LocalDateTime.now();
    }
}