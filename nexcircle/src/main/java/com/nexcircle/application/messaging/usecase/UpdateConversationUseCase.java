package com.nexcircle.application.messaging.usecase;

import com.nexcircle.application.messaging.dto.ConversationResponse;
import com.nexcircle.application.messaging.dto.UpdateConversation;
import com.nexcircle.application.messaging.mapper.ConversationMapper;
import com.nexcircle.domain.messaging.entity.Conversation;
import com.nexcircle.domain.messaging.repository.ConversationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateConversationUseCase {
    ConversationRepository conversationRepository;
    ConversationMapper conversationMapper;

    public ConversationResponse updateConversation(UpdateConversation request){
        Conversation conversation = this.conversationRepository.findById(request.getConversationId())
                .orElseThrow(() -> new RuntimeException("Conversation not found"));

        Conversation updated = this.conversationMapper.toUpdateMessage(request, conversation);

        return this.conversationMapper.toDto(this.conversationRepository.save(updated));
    }
}
