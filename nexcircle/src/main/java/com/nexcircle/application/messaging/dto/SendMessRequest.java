package com.nexcircle.application.messaging.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class SendMessRequest {
    UUID senderId;
    UUID conversationId;
    String content;
}
