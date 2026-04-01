package com.nexcircle.application.user.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FriendRequestDto {
    UUID senderId;
    UUID receiverId;
    String status;
}
