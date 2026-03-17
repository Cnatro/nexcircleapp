package com.nexcircle.domain.messaging.repository;

import com.nexcircle.domain.messaging.entity.Message;

public interface MessageRepository {
    Message save(Message message);
}
