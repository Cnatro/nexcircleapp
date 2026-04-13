package com.nexcircle.application.call.dto;

import com.nexcircle.domain.call.enums.CallType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CallRequest {
    @NotNull(message = "Receiver ID is required")
    UUID receiverId;

    @NotNull(message = "Call type is required")
    CallType type;
}