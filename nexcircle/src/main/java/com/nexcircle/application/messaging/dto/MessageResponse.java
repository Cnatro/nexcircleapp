package com.nexcircle.application.messaging.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class MessageResponse {
    UUID id;
    String content;
    LocalDateTime createdAt;
}
