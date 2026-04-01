package com.nexcircle.infrastructure.persistence.user.jpa;

import com.nexcircle.infrastructure.persistence.user.projection.FriendShipSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface FriendshipJpaRepository extends JpaRepository<FriendshipJpaEntity, UUID> {
    @Query(value = """
            select
                f.id as id,
                u.id as userId,
                u.full_name as fullName,
                u.avatar_url as avatar
            from friendships f
            join users u
                on u.id = case 
                            when f.user1_id = :userId then f.user2_id
                            else f.user1_id
                          end
            where (f.user1_id = :userId or f.user2_id = :userId)
              and f.status = 'active'
            """,
            countQuery = """
                    select count(*)
                    from friendships f
                    where (f.user1_id = :userId or f.user2_id = :userId)
                      and f.status = 'active'
                    """,
            nativeQuery = true)
    Page<FriendShipSummaryProjection> findAllFriends(UUID userId, Pageable pageable);

    @Query(value = """
            select count(f) > 0
            from friendships f
            where (f.user1_id = :user1Id and f.user2_id = :user2Id)
                   	or
                  (f.user2_id = :user1Id and f.user1_id = :user2Id)
            """, nativeQuery = true)
    boolean existsFriendship(UUID user1Id, UUID user2Id);
}
