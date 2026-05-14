package com.nexcircle.infrastructure.persistence.notification.jpa;

import com.nexcircle.infrastructure.persistence.user.jpa.UserJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_devices")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserDeviceJpaEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    private String deviceToken;
    private String platform;
    private LocalDateTime lastLogin;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
    }
}