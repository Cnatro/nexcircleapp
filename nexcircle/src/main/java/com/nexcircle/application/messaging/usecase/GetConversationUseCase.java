package com.nexcircle.application.messaging.usecase;

import com.nexcircle.application.messaging.dto.ConversationListItemDto;
import com.nexcircle.application.messaging.dto.ConversationParticipantItemDto;
import com.nexcircle.application.messaging.dto.MessageResponse;
import com.nexcircle.application.messaging.mapper.ConversationMapper;
import com.nexcircle.domain.messaging.repository.ConversationRepository;
import com.nexcircle.domain.user.service.SecurityContextService;
import com.nexcircle.infrastructure.persistence.messaging.projection.ConversationListItemProjection;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetConversationUseCase {
    ConversationRepository conversationRepository;
    ConversationMapper conversationMapper;
    SecurityContextService securityContextService;

    public List<ConversationListItemDto> findAllConversationWithUserLogin() {
        List<ConversationListItemProjection> results = this.conversationRepository
                .findAllConversationWithUserLogin(this.securityContextService.getCurrentUserId());

        Map<UUID, ConversationListItemDto> map = new LinkedHashMap<>();

        results.forEach(r -> {
            ConversationListItemDto dto = map.computeIfAbsent(r.getId(), id->{
                ConversationListItemDto d = new ConversationListItemDto();
                d.setId(id);
                d.setType(r.getType());
                d.setName(r.getName());
                d.setAvatar(r.getAvatar());
                d.setParticipants(new ArrayList<>());

                if (r.getMessageId() != null) {
                    d.setLastMessage(new MessageResponse(
                            r.getMessageId(),
                            r.getSenderId(),
                            r.getContent(),
                            r.getMessageType(),
                            r.getParentMessageId(),
                            r.getCreatedAt()
                    ));
                }

                d.setUnreadCount(0);
                d.setMute(false);

                return d;
            });

            if (r.getUserId() != null) {
                dto.getParticipants().add(
                        new ConversationParticipantItemDto(
                                r.getConversationParticipantId(),
                                r.getUserId(),
                                r.getFullName(),
                                r.getUsername(),
                                r.getAvatarUrl(),
                                r.getIsOnline()
                        )
                );
            }
        });

        return new ArrayList<>(map.values());
    }
}
