package com.nexcircle.domain.messaging.repository;

import com.nexcircle.domain.messaging.entity.Attachment;

import java.util.List;
import java.util.UUID;

public interface AttachmentRepository {
    List<Attachment> findByMessageIds(List<UUID> uuids);
}
