package com.nexcircle.presentation.controller;

import com.nexcircle.application.messaging.dto.MessageResponse;
import com.nexcircle.application.messaging.dto.SendMessRequest;
import com.nexcircle.application.messaging.usecase.SendMessUseCase;
import com.nexcircle.shared.dto.ApiResponse;
import com.nexcircle.shared.enums.MessageCode;
import com.nexcircle.shared.utils.ResponseFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/messages")
public class MessageController {
    SendMessUseCase sendMessUseCase;

    @PostMapping
    public ApiResponse<MessageResponse> send(@RequestBody SendMessRequest request) {

        MessageResponse response = this.sendMessUseCase.sendMessage(request);

        return ResponseFactory.success(
                MessageCode.MSG_SENT_SUCCESS,
                response
        );
    }
}
