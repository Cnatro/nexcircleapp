package com.nexcircle.infrastructure.persistence.messaging;

import com.nexcircle.domain.messaging.entity.Message;
import com.nexcircle.domain.messaging.repository.MessageRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MessageRepesitoryImpl implements MessageRepository {
    MessageJpaRepository messageJpaRepository;

    @Override
    public Message save(Message message) {
        return this.messageJpaRepository.save(message);
    }
}
