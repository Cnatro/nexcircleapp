package com.nexcircle.shared.utils;

import com.nexcircle.shared.dto.ApiResponse;
import com.nexcircle.shared.enums.MessageCode;
import com.nexcircle.shared.enums.ResponseStatus;
import org.springframework.http.HttpStatus;

public class ResponseFactory {

    private static HttpStatus mapHttpStatus(MessageCode code) {
        switch (code) {
            case CREATED_SUCCESS:
            case MSG_SENT_SUCCESS:
            case USER_CREATED_SUCCESS:
            case USER_LOGIN_SUCCESS:
            case GET_USER_SUCCESS:
            case CALL_INITIATED_SUCCESS:
                return HttpStatus.OK; // 200
            case USER_NOT_FOUND:
            case CALL_NOT_FOUND:
            case INVALID_INPUT:
            case MSG_SENT_FAILED:
                return HttpStatus.BAD_REQUEST; // 400
            case FAIL:
            case CALL_FAILED:
            case INTERNAL_SERVER_ERROR:
                return HttpStatus.INTERNAL_SERVER_ERROR; // 500
            default:
                return HttpStatus.OK; // mặc định 200
        }
    }
    public static <T> ApiResponse<T> success(MessageCode code, T data) {
        return ApiResponse.<T>builder()
                .status(String.valueOf(mapHttpStatus(code).value()))
                .messageCode(code.name())
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(MessageCode code) {
        return ApiResponse.<T>builder()
                .status(String.valueOf(mapHttpStatus(code).value()))
                .messageCode(code.name())
                .build();
    }
}
