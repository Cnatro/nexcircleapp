package com.nexcircle.application.user.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FriendShipResponseDto {
    UUID id;
    UUID userId;
    String fullName;
    String avatar;
}
