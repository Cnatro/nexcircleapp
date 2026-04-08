package com.nexcircle.application.messaging.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachmentSummary {
    UUID id;
    String fileUrl;
    String fileType;
    Integer fileSize;
}
