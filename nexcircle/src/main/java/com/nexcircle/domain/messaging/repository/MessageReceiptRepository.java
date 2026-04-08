package com.nexcircle.domain.messaging.repository;

import com.nexcircle.domain.messaging.entity.MessageReceipt;

import java.util.List;
import java.util.UUID;

public interface MessageReceiptRepository {
    List<MessageReceipt> findByMessageIds(List<UUID> uuids);
}
