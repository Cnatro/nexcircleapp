package com.nexcircle.infrastructure.persistence.user;

import com.nexcircle.domain.user.entity.User;
import com.nexcircle.domain.user.repository.UserRepository;
import com.nexcircle.infrastructure.persistence.user.jpa.UserJpaEntity;
import com.nexcircle.infrastructure.persistence.user.jpa.UserJpaRepository;
import com.nexcircle.infrastructure.persistence.user.mapper.UserPersistenceMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRepositoryImpl implements UserRepository {
    UserJpaRepository userJpaRepository;
    UserPersistenceMapper userMapper;

    @Override
    public User save(User user) {
        var jpa = userMapper.toUserJpaEntity(user);
        var saved = userJpaRepository.save(jpa);
        return userMapper.toUserDomain(saved);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userJpaRepository.findByUsername(username).map(userMapper::toUserDomain);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return this.userJpaRepository.findById(id).map(userMapper::toUserDomain);
    }

    @Override
    public List<User> findUserInIds(List<UUID> ids) {
        return this.userJpaRepository.findAllById(ids).stream().map(userMapper::toUserDomain).toList();
    }

    @Override
    public User findUserReceiptMessageByConversationIdAndUserId(UUID converId, UUID userSenderId) {
        UserJpaEntity userJpa = this.userJpaRepository.findUserReceiptMessageByConversationIdAndUserId(converId, userSenderId);
        return this.userMapper.toUserDomain(userJpa);
    }

    @Override
    public Page<User> findNearByUsers(UUID currentUserId, Pageable pageable) {
        return this.userJpaRepository
                .findByIdNot(currentUserId,pageable)
                .map(this.userMapper::toUserDomain);
    }
}
