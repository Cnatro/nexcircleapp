package com.nexcircle.domain.notification.entity;

import com.nexcircle.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_devices")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserDevice {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String deviceToken;
    private String platform;
    private LocalDateTime lastLogin;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
    }
}