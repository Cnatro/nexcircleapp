package com.nexcircle.domain.messaging.entity;

import com.nexcircle.domain.user.entity.User;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Message {
    private UUID id;
    private Conversation conversation;
    private User sender;
    private String content;
    private String messageType;
    private String status;
    private Message parentMessage;
    private LocalDateTime createdAt;

    public static Message create(
            Conversation conversation,
            User sender,
            String content
    ) {
        return new Message(
                UUID.randomUUID(),
                conversation,
                sender,
                content,
                "TEXT",
                "SENT",
                null,
                LocalDateTime.now()
        );
    }
}