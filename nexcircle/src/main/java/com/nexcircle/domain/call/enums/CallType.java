package com.nexcircle.domain.call.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum CallType {
    AUDIO,
    VIDEO,
}
