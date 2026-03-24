package com.nexcircle.application.messaging.usecase;

import com.nexcircle.application.messaging.dto.ConversationRequest;
import com.nexcircle.application.messaging.dto.ConversationResponse;
import com.nexcircle.application.messaging.mapper.ConversationMapper;
import com.nexcircle.domain.messaging.entity.Conversation;
import com.nexcircle.domain.messaging.entity.ConversationParticipant;
import com.nexcircle.domain.messaging.repository.ConversationParticipantRepository;
import com.nexcircle.domain.messaging.repository.ConversationRepository;
import com.nexcircle.domain.user.entity.User;
import com.nexcircle.domain.user.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateConversationUseCase {
    ConversationRepository conversationRepository;
    UserRepository userRepository;
    ConversationParticipantRepository conversationParticipantRepository;
    ConversationMapper conversationMapper;

    public ConversationResponse createConversation(ConversationRequest request) {
        Conversation conversation = this.conversationMapper.toEntity(request);
        Conversation savedConversation = conversationRepository.save(conversation);

        List<User> users = this.userRepository.findUserInIds(request.getUserIds());
        List<ConversationParticipant> conversationParticipants = users.stream()
                .map(user -> ConversationParticipant.builder()
                        .conversation(savedConversation)
                        .user(user)
                        .build()
                ).toList();

        this.conversationParticipantRepository.saveAll(conversationParticipants);
        return this.conversationMapper.toDto(conversation);
    }


}
