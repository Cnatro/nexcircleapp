package com.nexcircle.application.notification.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Map;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageFirebaseDto {
    Notification notification;
    Data data;
    Apns apns;
    Android android;
    String token;

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Notification {
        String title;
        String body;
    }

    // ================== Data ==================
    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Data {
        String payload; // JSON string
    }

    // ================== Android ==================
    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Android {
        String priority; // "high"
    }

    // ================== APNS ==================
    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Apns {
        Map<String, String> headers;
        ApnsPayload payload;
    }

    // ================== APNS Payload ==================
    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ApnsPayload {
        Aps aps;
    }

    // ================== APS ==================
    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Aps {
        ApsAlert alert;
        String sound;
        Integer badge;
        Boolean mutableContent;
        Boolean contentAvailable;
    }

    // ================== APS Alert ==================
    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ApsAlert {
        String title;
        String body;
    }
}
