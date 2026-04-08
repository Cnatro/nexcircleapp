package com.nexcircle.application.messaging.dto;

import com.nexcircle.application.user.dto.UserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageView {
    UUID id;

    String content;
    String messageType;
    LocalDateTime createdAt;
    UUID parentMessageId;

    UserResponse sender;
    List<AttachmentSummary> attachments;
    List<MessageReceiptSummary> messageReceipts;
}