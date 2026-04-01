package com.nexcircle.infrastructure.persistence.user;

import com.nexcircle.domain.user.entity.FriendRequest;
import com.nexcircle.domain.user.repository.FriendRequestRepository;
import com.nexcircle.infrastructure.persistence.user.jpa.FriendRequestJpaEntity;
import com.nexcircle.infrastructure.persistence.user.jpa.FriendRequestJpaRepository;
import com.nexcircle.infrastructure.persistence.user.mapper.FriendRequestPersistenceMapper;
import com.nexcircle.infrastructure.persistence.user.projection.FriendRequestInfoProjection;
import com.nexcircle.infrastructure.persistence.user.projection.FriendRequestSummaryProjection;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FriendRequestRepositoryImpl implements FriendRequestRepository {
    FriendRequestJpaRepository jpaRepository;
    FriendRequestPersistenceMapper persistenceMapper;

    @Override
    public FriendRequest save(FriendRequest friendRequest) {
        FriendRequestJpaEntity jpa = this.persistenceMapper.toUserJpaEntity(friendRequest);
        return this.persistenceMapper.toUserDomain(this.jpaRepository.save(jpa));
    }

    @Override
    public FriendRequestInfoProjection updateAndReturn(UUID id) {
        return this.jpaRepository.updateAndReturn(id);
    }

    @Override
    public Page<FriendRequestSummaryProjection> findAllByStatusAndReceiverId(String status, UUID receiverId, Pageable pageable) {
        return this.jpaRepository.findAllByStatusAndReceiverId(status,receiverId, pageable);
    }


}
