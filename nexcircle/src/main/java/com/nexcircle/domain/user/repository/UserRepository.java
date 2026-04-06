package com.nexcircle.domain.user.repository;

import com.nexcircle.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    Optional<User> findByUsername(String username);
    Optional<User> findById(UUID id);
    List<User>findUserInIds(List<UUID> ids);
    User findUserReceiptMessageByConversationIdAndUserId(UUID converId, UUID userSenderId);
    Page<User> findNearByUsers(UUID currentUserId,Pageable pageable);
}
