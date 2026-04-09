package com.nexcircle.shared.enums;

public enum MessageType {
    MESSAGE,
    IMAGE,
    FILE,
    VIDEO,
    AUDIO,

    NEW_MESSAGE,
    MESSAGE_SEEN,
    MESSAGE_DELIVERED,
    TYPING,
    STOP_TYPING,

    FRIEND_REQUEST,
    FRIEND_ACCEPTED,
    FRIEND_REJECTED,
    FRIEND_REMOVED,

    SYSTEM_NOTIFICATION,
    WARNING,
    ERROR,

    CALL_INVITE,
    CALL_ACCEPT,
    CALL_REJECT,
    CALL_END
}
