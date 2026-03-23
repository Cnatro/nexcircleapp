package com.nexcircle.infrastructure.persistence.messaging.jpa;

import com.nexcircle.domain.messaging.entity.Message;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "attachments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AttachmentJpaEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "message_id", nullable = false)
    private MessageJpaEntity message;

    private String fileUrl;
    private String fileType;
    private Integer fileSize;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
    }
}