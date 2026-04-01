package com.nexcircle.presentation.controller;

import com.nexcircle.application.user.dto.AcceptFriendRequestDto;
import com.nexcircle.application.user.dto.FriendRequestDto;
import com.nexcircle.application.user.dto.FriendRequestFilter;
import com.nexcircle.application.user.dto.FriendRqResponseDto;
import com.nexcircle.application.user.usecase.AcceptFriendRequestUseCase;
import com.nexcircle.application.user.usecase.GetSentFriendRequestsUseCase;
import com.nexcircle.application.user.usecase.SendFriendRequestUseCase;
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
@RequestMapping("/friend-requests")
@Slf4j
public class FriendRequestController {
    SendFriendRequestUseCase sendFriendRequestUseCase;
    GetSentFriendRequestsUseCase getSentFriendRequestsUseCase;
    AcceptFriendRequestUseCase acceptFriendRequestUseCase;

    @PostMapping("/add")
    public ApiResponse<String> sentFrRequest(@RequestBody FriendRequestDto dto) {
        this.sendFriendRequestUseCase.createFrRequest(dto);
        return ResponseFactory.success(
                MessageCode.CREATED_SUCCESS,
                null
        );
    }

    @GetMapping()
    public ApiResponse<Map<String, Object>> getFrRequests(@ModelAttribute FriendRequestFilter filter) {
        Page<FriendRqResponseDto> rqResponseDtos = this.getSentFriendRequestsUseCase.getFriendRequestsByStatusAndReceiverId(filter);

        return ResponseFactory.success(
                MessageCode.SUCCESS,
                Map.of(
                        "data", rqResponseDtos.getContent(),
                        "total", rqResponseDtos.getTotalPages()
                )
        );
    }

    @PatchMapping("/accept")
    public ApiResponse<String> acceptFriendRequest(@RequestBody AcceptFriendRequestDto dto) {
        this.acceptFriendRequestUseCase.acceptFriendRequest(dto);

        return ResponseFactory.success(
                MessageCode.SUCCESS,
                null
        );
    }
}
