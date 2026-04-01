package com.nexcircle.presentation.controller;

import com.nexcircle.application.user.dto.FriendShipFilter;
import com.nexcircle.application.user.dto.FriendShipResponseDto;
import com.nexcircle.application.user.usecase.GetFriendShipUseCase;
import com.nexcircle.shared.dto.ApiResponse;
import com.nexcircle.shared.enums.MessageCode;
import com.nexcircle.shared.utils.ResponseFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/friend-ships")
@Slf4j
public class FriendShipController {
    GetFriendShipUseCase getFriendShipUseCase;

    @GetMapping
    public ApiResponse<Map<String, Object>> getFriendShipsByCondition(@ModelAttribute FriendShipFilter filter) {
        Page<FriendShipResponseDto> dtos = this.getFriendShipUseCase.getFriendShipsByUserLogin(filter);

        return ResponseFactory.success(
                MessageCode.SUCCESS,
                Map.of(
                        "data", dtos.getContent(),
                        "total", dtos.getTotalPages()
                )
        );
    }
}
