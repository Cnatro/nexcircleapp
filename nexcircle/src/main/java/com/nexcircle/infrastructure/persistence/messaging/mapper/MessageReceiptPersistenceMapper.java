package com.nexcircle.infrastructure.persistence.messaging.mapper;

import com.nexcircle.domain.messaging.entity.Message;
import com.nexcircle.domain.messaging.entity.MessageReceipt;
import com.nexcircle.infrastructure.persistence.messaging.jpa.MessageJpaEntity;
import com.nexcircle.infrastructure.persistence.messaging.jpa.MessageReceiptJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MessageReceiptPersistenceMapper {
    MessageReceipt toDomain(MessageReceiptJpaEntity jpa);
    MessageJpaEntity toJpaEntity(Message message);
}
