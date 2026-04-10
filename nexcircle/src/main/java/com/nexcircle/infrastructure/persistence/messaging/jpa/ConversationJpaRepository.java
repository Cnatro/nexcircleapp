package com.nexcircle.infrastructure.persistence.messaging.jpa;

import com.nexcircle.domain.messaging.entity.Conversation;
import com.nexcircle.infrastructure.persistence.messaging.projection.ConversationListItemProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConversationJpaRepository extends JpaRepository<ConversationJpaEntity, UUID> {
    @Query(value = """
            with user_conversations as (
                select conversation_id
                from conversation_participants
                where user_id = :userId
            ),
            participants as (
                select
                    cp.id,
                    cp.conversation_id,
                    cp.user_id,
                    u.full_name,
                    u.username,
                    u.avatar_url,
                    u.is_online
                from conversation_participants cp
                join users u on u.id = cp.user_id
                where cp.conversation_id in (select conversation_id from user_conversations) and cp.user_id <> :userId
            ),
            last_message as (
                select
                    m.id,
                    m.sender_id,
                    m.content,
                    m.message_type,
                    m.created_at,
                    c.id as conversation_id,
                    m.parent_message_id
                from conversations c
                left join messages m on m.id = c.last_message_id
            )
            select
                c.id,
                c.type,
                c.name,
                c.avatar,
            
                p.id as conversation_participant_id,
                p.user_id,
                p.full_name,
                p.username,
                p.avatar_url,
                p.is_online,
            
                lm.id as message_id,
                lm.sender_id,
                lm.content,
                lm.message_type,
                lm.created_at,
                lm.parent_message_id
            
            from conversations c
            join user_conversations uc on uc.conversation_id = c.id
            left join participants p on p.conversation_id = c.id
            left join last_message lm on lm.conversation_id = c.id
            """,
            nativeQuery = true)
    List<ConversationListItemProjection> findAllConversationWithUserLogin(UUID userId);

    @Query(value = """
            select c.*
            from conversations c
            join conversation_participants cp on cp.conversation_id = c.id
            where c.type = :type
            group by c.id
            having
                count(distinct cp.user_id) = :size
                and count(distinct case when cp.user_id in (:userIds) then cp.user_id end) = :size
            limit 1
            """, nativeQuery = true)
    Optional<ConversationJpaEntity> findExactConversation(
            @Param("userIds") List<UUID> userIds,
            @Param("size") long size,
            @Param("type") String type
    );

    @Query(value = """
                select
                    c.id,
                    c.type,
                    c.name,
                    c.avatar,
            
                    p.id as conversation_participant_id,
                    p.id as user_id,
                    p.full_name,
                    p.username,
                    p.avatar_url,
                    p.is_online,
            
                    lm.id as message_id,
                    lm.sender_id,
                    lm.content,
                    lm.message_type,
                    lm.created_at,
                    lm.parent_message_id
            
                from conversations c
            
                left join conversation_participants cp 
                    on cp.conversation_id = c.id
            
                left join users p 
                    on p.id = cp.user_id
            
                left join messages lm 
                    on lm.id = c.last_message_id
            
                where c.id = :conversationId
            """, nativeQuery = true)
    List<ConversationListItemProjection> findConversationDetail(UUID conversationId);
}
