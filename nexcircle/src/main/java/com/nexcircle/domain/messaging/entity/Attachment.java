package com.nexcircle.domain.messaging.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "attachments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Attachment {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "message_id", nullable = false)
    private Message message;

    private String fileUrl;
    private String fileType;
    private Integer fileSize;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
    }
}