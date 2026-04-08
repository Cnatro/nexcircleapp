package com.nexcircle.application.messaging.usecase;

import com.nexcircle.application.messaging.dto.MessageFilter;
import com.nexcircle.application.messaging.dto.MessageView;
import com.nexcircle.application.messaging.mapper.AttachmentMapper;
import com.nexcircle.application.messaging.mapper.MessageMapper;
import com.nexcircle.application.messaging.mapper.MessageReceiptMapper;
import com.nexcircle.application.user.dto.UserResponse;
import com.nexcircle.application.user.mapper.UserMapper;
import com.nexcircle.domain.messaging.entity.Attachment;
import com.nexcircle.domain.messaging.entity.Message;
import com.nexcircle.domain.messaging.entity.MessageReceipt;
import com.nexcircle.domain.messaging.repository.AttachmentRepository;
import com.nexcircle.domain.messaging.repository.ConversationRepository;
import com.nexcircle.domain.messaging.repository.MessageReceiptRepository;
import com.nexcircle.domain.messaging.repository.MessageRepository;
import com.nexcircle.domain.user.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetMessagesUseCase {
    MessageRepository messageRepository;
    MessageMapper messageMapper;
    UserRepository userRepository;
    ConversationRepository conversationRepository;
    AttachmentRepository attachmentRepository;
    MessageReceiptRepository messageReceiptRepository;
    UserMapper userMapper;
    AttachmentMapper attachmentMapper;
    MessageReceiptMapper messageReceiptMapper;

    public Page<MessageView> getMessageViews(MessageFilter filter){
        Pageable pageable = PageRequest.of(filter.getPage(),filter.getSize(), Sort.by("createdAt").ascending());

        Page<Message> messagesPage = this.messageRepository.findAllByConversationId(filter.getConversationId(), pageable);

        List<UUID> messageIds = messagesPage.getContent().stream().map(Message::getId).toList();
        List<UUID> senderIds = messagesPage.getContent().stream().map(message -> message.getSender().getId()).toList();

        if (messageIds.isEmpty() || senderIds.isEmpty()) {
            return messagesPage.map(messageMapper::toMessageView);
        }

        List<Attachment> attachments = this.attachmentRepository.findByMessageIds(messageIds);
        List<MessageReceipt> messageReceipts = this.messageReceiptRepository.findByMessageIds(messageIds);
        List<UserResponse> users = this.userRepository.findUserInIds(senderIds).stream().map(this.userMapper::toDto).toList();

        Map<UUID, List<Attachment>> attachmentMap = attachments.stream()
                .collect(Collectors.groupingBy(attachment -> attachment.getMessage().getId()));

        Map<UUID, List<MessageReceipt>> messageReceiptMap = messageReceipts.stream()
                .collect(Collectors.groupingBy(messageReceipt -> messageReceipt.getMessage().getId()));

        Map<UUID, UserResponse> userMap = users.stream()
                .collect(Collectors.toMap(UserResponse::getId, Function.identity()));


        List<MessageView> messageViews = messagesPage.getContent().stream()
                .map(message -> {
                    MessageView messageView = this.messageMapper.toMessageView(message);

                    messageView.setAttachments(
                            attachmentMap.getOrDefault(message.getId(),List.of())
                                    .stream()
                                    .map(this.attachmentMapper::toDto)
                                    .toList()
                    );

                    messageView.setMessageReceipts(
                            messageReceiptMap.getOrDefault(message.getId(),List.of())
                                    .stream()
                                    .map(this.messageReceiptMapper::toDto)
                                    .toList()
                    );

                    messageView.setSender(userMap.get(message.getSender().getId()));

                    return messageView;
                }).toList();

        return new PageImpl<>(messageViews,pageable, messagesPage.getTotalElements());
    }
}
