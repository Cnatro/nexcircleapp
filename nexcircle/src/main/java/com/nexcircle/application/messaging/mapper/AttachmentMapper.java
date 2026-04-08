package com.nexcircle.application.messaging.mapper;

import com.nexcircle.application.messaging.dto.AttachmentSummary;
import com.nexcircle.domain.messaging.entity.Attachment;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AttachmentMapper {
    AttachmentSummary toDto(Attachment attachment);
}
