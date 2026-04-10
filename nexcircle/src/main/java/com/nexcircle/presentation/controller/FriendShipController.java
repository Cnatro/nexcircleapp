package com.nexcircle.presentation.controller;

import com.nexcircle.application.user.dto.FriendShipFilter;
import com.nexcircle.application.user.dto.FriendShipRequestDto;
import com.nexcircle.application.user.dto.FriendShipResponseDto;
import com.nexcircle.application.user.usecase.GetFriendShipUseCase;
import com.nexcircle.application.user.usecase.HandleFriendShipUseCase;
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
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/friend-ships")
@Slf4j
public class FriendShipController {
    GetFriendShipUseCase getFriendShipUseCase;
    HandleFriendShipUseCase handleFriendShipUseCase;

    @GetMapping
    public ApiResponse<Map<String, Object>> getFriendShipsByCondition(@ModelAttribute FriendShipFilter filter) {
        Page<FriendShipResponseDto> dtos = this.getFriendShipUseCase.getFriendShipsByUserLogin(filter);

        return ResponseFactory.success(
                MessageCode.SUCCESS,
                Map.of(
                        "data", dtos.getContent(),
                        "total", dtos.getTotalElements()
                )
        );
    }

    @PatchMapping("/{fsId}/status")
    public ApiResponse<FriendShipResponseDto> updateStatus(@PathVariable UUID fsId, @RequestBody FriendShipRequestDto dto) {
        FriendShipResponseDto response = this.handleFriendShipUseCase.updateStatusFriendShip(fsId, dto);

        return ResponseFactory.success(
                MessageCode.SUCCESS,
                response
        );
    }
}
