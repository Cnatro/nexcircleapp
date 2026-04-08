package com.nexcircle.application.messaging.mapper;

import com.nexcircle.application.messaging.dto.MessageReceiptSummary;
import com.nexcircle.domain.messaging.entity.MessageReceipt;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MessageReceiptMapper {
    MessageReceiptSummary toDto(MessageReceipt messageReceipt);
}
