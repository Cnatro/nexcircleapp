package com.nexcircle.infrastructure.persistence.user;

import com.nexcircle.domain.user.entity.Friendship;
import com.nexcircle.domain.user.repository.FriendshipRepository;
import com.nexcircle.infrastructure.persistence.user.jpa.FriendshipJpaEntity;
import com.nexcircle.infrastructure.persistence.user.jpa.FriendshipJpaRepository;
import com.nexcircle.infrastructure.persistence.user.mapper.FriendshipPersistenceMapper;
import com.nexcircle.infrastructure.persistence.user.projection.FriendShipSummaryProjection;
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
public class FriendshipRepositoryImpl implements FriendshipRepository {
    FriendshipJpaRepository jpaRepository;
    FriendshipPersistenceMapper persistenceMapper;

    @Override
    public Friendship save(Friendship friendship) {
        FriendshipJpaEntity jpa = this.persistenceMapper.toFriendshipJpaEntity(friendship);
        return this.persistenceMapper.toDomain(this.jpaRepository.save(jpa));
    }

    @Override
    public Page<FriendShipSummaryProjection> findAllByUserId(UUID userId, Pageable pageable) {
        return this.jpaRepository.findAllFriends(userId, pageable);
    }

    @Override
    public boolean isExistsFriendship(UUID user1Id, UUID user2Id) {
        return this.jpaRepository.existsFriendship(user1Id,user2Id);
    }
}
