package com.nexcircle.presentation.websocket;

import com.nexcircle.application.messaging.dto.MessageResponse;
import com.nexcircle.application.messaging.dto.SendMessRequest;
import com.nexcircle.application.messaging.usecase.SendMessUseCase;
import com.nexcircle.application.user.dto.UserResponse;
import com.nexcircle.application.user.usecase.LoadUserUseCase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ChatWebSocketHandler {
    SendMessUseCase sendMessUseCase;
    LoadUserUseCase loadUserUseCase;
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/send/group")
    public void sendToGroup(@Payload SendMessRequest request){
//        MessageResponse res = this.sendMessUseCase.sendMessage(request);
//        this.simpMessagingTemplate.convertAndSend();
    }

    @MessageMapping("/send/private")
    public void sendToUser(@Payload SendMessRequest request){
            UserResponse userReceipt = this.loadUserUseCase.getUserReceiptMessage(request);
            MessageResponse res = this.sendMessUseCase.sendMessage(request);

            this.simpMessagingTemplate.convertAndSendToUser(
                    userReceipt.getUsername(),
                    "/private/messages",
                    res
            );
    }
}
