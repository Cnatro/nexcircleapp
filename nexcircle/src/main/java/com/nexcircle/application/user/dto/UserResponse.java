package com.nexcircle.application.user.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    UUID id;
    String username;
    String email;
    String fullName;
    String avatarUrl;
    String description;

    Boolean isOnline;
    LocalDateTime lastActive;
    LocalDateTime createdAt;
}
