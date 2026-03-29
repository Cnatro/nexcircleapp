package com.nexcircle.shared.enums;

public enum MessageCode {
    CREATED_SUCCESS,
    FAIL,
    // ===== MESSAGE =====
    MSG_SENT_SUCCESS,
    MSG_SENT_FAILED,

    // ===== USER =====
    USER_CREATED_SUCCESS,
    USER_NOT_FOUND,
    USER_LOGIN_SUCCESS,
    GET_USER_SUCCESS,

    //==== Call ====
    CALL_INITIATED_SUCCESS,
    CALL_NOT_FOUND,
    CALL_FAILED,
    CALL_PARTICIPANT_NOT_FOUND,
    CALL_ACCEPT,
    CALL_END,
    CALL_REJECT,
    CALL_CANCEL,


    INVALID_INPUT,
    INTERNAL_SERVER_ERROR,

    UNAUTHORIZED,


}
