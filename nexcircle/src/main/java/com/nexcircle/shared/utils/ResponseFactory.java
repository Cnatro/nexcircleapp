package com.nexcircle.shared.utils;

import com.nexcircle.shared.dto.ApiResponse;
import com.nexcircle.shared.enums.MessageCode;
import com.nexcircle.shared.enums.ResponseStatus;

public class ResponseFactory {
    public static <T> ApiResponse<T> success(MessageCode code, T data) {
        return ApiResponse.<T>builder()
                .status(ResponseStatus.SUCCESS.name().toLowerCase())
                .messageCode(code.name())
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(MessageCode code) {
        return ApiResponse.<T>builder()
                .status(ResponseStatus.ERROR.name().toLowerCase())
                .messageCode(code.name())
                .build();
    }
}
