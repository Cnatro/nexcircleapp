package com.nexcircle.domain.location.entity;

import com.nexcircle.domain.user.entity.User;
import com.nexcircle.infrastructure.persistence.user.jpa.UserJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Location {
    private UUID id;
    private User user;
    private Float latitude;
    private Float longitude;
    private Float accuracy;
    private LocalDateTime createdAt;
}