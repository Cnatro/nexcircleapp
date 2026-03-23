package com.nexcircle.domain.messaging.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Attachment {
    private UUID id;
    private Message message;
    private String fileUrl;
    private String fileType;
    private Integer fileSize;
}