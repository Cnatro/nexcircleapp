package com.nexcircle.presentation.controller;

import com.nexcircle.application.messaging.dto.MessageFilter;
import com.nexcircle.application.messaging.dto.MessageResponse;
import com.nexcircle.application.messaging.dto.MessageView;
import com.nexcircle.application.messaging.dto.SendMessRequest;
import com.nexcircle.application.messaging.usecase.GetMessagesUseCase;
import com.nexcircle.application.messaging.usecase.SendMessUseCase;
import com.nexcircle.shared.dto.ApiResponse;
import com.nexcircle.shared.enums.MessageCode;
import com.nexcircle.shared.utils.ResponseFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/messages")
@Slf4j
public class MessageController {
    GetMessagesUseCase getMessagesUseCase;

    @GetMapping
    public ApiResponse<Map<String, Object>> send(@ModelAttribute MessageFilter filter) {
        log.debug("send message");
        Page<MessageView>  messageViews = this.getMessagesUseCase.getMessageViews(filter);

        return ResponseFactory.success(
                MessageCode.SUCCESS,
                Map.of("data",messageViews.getContent(),
                        "total", messageViews.getTotalElements()
                )
        );
    }
}
