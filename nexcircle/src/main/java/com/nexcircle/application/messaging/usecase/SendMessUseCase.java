package com.nexcircle.application.messaging.usecase;

import com.nexcircle.application.messaging.dto.MessageResponse;
import com.nexcircle.application.messaging.dto.SendMessRequest;
import com.nexcircle.application.messaging.mapper.MessageMapper;
import com.nexcircle.domain.messaging.entity.Message;
import com.nexcircle.domain.messaging.repository.MessageRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SendMessUseCase {
    MessageRepository messageRepository;
    MessageMapper messageMapper;

    public MessageResponse sendMessage(SendMessRequest request){
        Message mess = this.messageMapper.toEntity(request);

        return this.messageMapper.toDto(this.messageRepository.save(mess));
    }

//    public MessageResponse sendMessage(SendMessRequest request){
//
//        Conversation conversation = conversationRepository.findById(request.getConversationId())
//                .orElseThrow(...);
//
//        User sender = userRepository.findById(request.getSenderId())
//                .orElseThrow(...);
//
//        Message mess = Message.create(
//                conversation,
//                sender,
//                request.getContent()
//        );
//
//        return messageMapper.toDto(messageRepository.save(mess));
//    }
}
