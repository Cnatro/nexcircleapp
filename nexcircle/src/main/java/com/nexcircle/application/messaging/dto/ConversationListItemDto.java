package com.nexcircle.application.messaging.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConversationListItemDto {
    UUID id;
    String type;
    String name;
    String avatar;
    List<ConversationParticipantItemDto> participants;
    MessageResponse lastMessage;
    int unreadCount;
    boolean isMute; // tắt thông báo hay không
}
