package com.nexcircle.presentation.controller;

import com.nexcircle.application.call.dto.CallRequest;
import com.nexcircle.application.call.dto.CallResponse;
import com.nexcircle.application.call.usecase.AcceptCallUseCase;
import com.nexcircle.application.call.usecase.InitiateCallUseCase;
import com.nexcircle.shared.dto.ApiResponse;
import com.nexcircle.shared.enums.MessageCode;
import com.nexcircle.shared.utils.ResponseFactory;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Call;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/calls")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CallController {
    InitiateCallUseCase initiateCallUseCase;
    AcceptCallUseCase acceptCallUseCase;

    @PostMapping("/initiate")
    public ApiResponse<CallResponse> initiate(@Valid @RequestBody CallRequest request){
        CallResponse response = initiateCallUseCase.execute(request);
        return ResponseFactory.success(MessageCode.CALL_INITIATED_SUCCESS, response);
    }

    @PatchMapping("/{sessionId}/accept")
    public ApiResponse<CallResponse> acceptCall(@PathVariable UUID sessionId) {
        CallResponse response = acceptCallUseCase.execute(sessionId);
        return ResponseFactory.success(MessageCode.CALL_ACCEPT_ACCEPT, response);
    }
}
