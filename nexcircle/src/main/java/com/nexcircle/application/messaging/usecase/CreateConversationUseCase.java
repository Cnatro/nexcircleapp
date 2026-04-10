package com.nexcircle.application.messaging.usecase;

import com.nexcircle.application.messaging.dto.ConversationListItemDto;
import com.nexcircle.application.messaging.dto.ConversationRequest;
import com.nexcircle.application.messaging.dto.ConversationResponse;
import com.nexcircle.application.messaging.mapper.ConversationMapper;
import com.nexcircle.domain.messaging.entity.Conversation;
import com.nexcircle.domain.messaging.entity.ConversationParticipant;
import com.nexcircle.domain.messaging.repository.ConversationParticipantRepository;
import com.nexcircle.domain.messaging.repository.ConversationRepository;
import com.nexcircle.domain.user.entity.User;
import com.nexcircle.domain.user.repository.UserRepository;
import com.nexcircle.domain.user.service.SecurityContextService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateConversationUseCase {
    ConversationRepository conversationRepository;
    UserRepository userRepository;
    ConversationParticipantRepository conversationParticipantRepository;
    ConversationMapper conversationMapper;
    SecurityContextService securityContextService;
    GetConversationUseCase getConversationUseCase;

    public ConversationListItemDto createConversation(ConversationRequest request) {

        Set<UUID> userSet = new HashSet<>(request.getUserIds());
        userSet.add(this.securityContextService.getCurrentUserId());

        List<UUID> allUserIds = new ArrayList<>(userSet);

        Optional<Conversation> existingConversation =
                this.conversationRepository
                        .findExactConversation(allUserIds, allUserIds.size(),request.getType());

        if(existingConversation.isPresent()){
            return this.getConversationUseCase.getDetail(existingConversation.get().getId());
        }

        Conversation conversation = this.conversationMapper.toEntity(request);
        Conversation savedConversation = conversationRepository.save(conversation);

        List<User> users = this.userRepository.findUserInIds(allUserIds);
        List<ConversationParticipant> conversationParticipants = users.stream()
                .map(user -> ConversationParticipant.builder()
                        .conversation(savedConversation)
                        .user(user)
                        .build()
                ).toList();

        this.conversationParticipantRepository.saveAll(conversationParticipants);
        return this.getConversationUseCase.getDetail(savedConversation.getId());
    }


}
