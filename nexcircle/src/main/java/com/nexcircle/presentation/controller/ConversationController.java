package com.nexcircle.presentation.controller;

import com.nexcircle.application.messaging.dto.ConversationListItemDto;
import com.nexcircle.application.messaging.dto.ConversationRequest;
import com.nexcircle.application.messaging.dto.ConversationResponse;
import com.nexcircle.application.messaging.usecase.CreateConversationUseCase;
import com.nexcircle.application.messaging.usecase.GetConversationUseCase;
import com.nexcircle.shared.dto.ApiResponse;
import com.nexcircle.shared.enums.MessageCode;
import com.nexcircle.shared.utils.ResponseFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/conversations")
@Slf4j
public class ConversationController {
    CreateConversationUseCase createConversationUseCase;
    GetConversationUseCase conversationUseCase;

    @PostMapping
    public ApiResponse<ConversationResponse> createConversation(@RequestBody ConversationRequest request) {
        log.debug("create conversation");
        ConversationResponse response = this.createConversationUseCase.createConversation(request);
        return ResponseFactory.success(
                MessageCode.CREATED_SUCCESS,
                response
        );
    }

    @GetMapping
    public ApiResponse<List<ConversationListItemDto>> findAllConversationWithUserLogin(){
        List<ConversationListItemDto> responses = this.conversationUseCase.findAllConversationWithUserLogin();

        return ResponseFactory.success(
                MessageCode.SUCCESS,
                responses
        );
    }
}
