package com.nexcircle.domain.messaging.repository;

import com.nexcircle.domain.messaging.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface MessageRepository {
    Message save(Message message);
    Page<Message> findAllByConversationId(UUID conversationId, Pageable pageable);
}
