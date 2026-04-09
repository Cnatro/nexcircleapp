package com.nexcircle.infrastructure.external;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import com.nexcircle.application.notification.dto.MessageFirebaseDto;
import com.nexcircle.application.notification.dto.NotificationFirebaseDto;
import com.nexcircle.application.notification.usecase.NotificationService;
import com.nexcircle.application.notification.usecase.UserDeviceQueryService;
import com.nexcircle.domain.notification.entity.UserDevice;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FirebaseService {
    UserDeviceQueryService userDeviceQueryService;
    NotificationService notificationService;
    ObjectMapper objectMapper;

    @PostConstruct
    public void init() throws IOException {
        // Load file từ classpath
        InputStream serviceAccount = getClass().getClassLoader()
                .getResourceAsStream("firebase/nexcircleapp-firebase-adminsdk-fbsvc-2ed9490556.json");

        if(serviceAccount == null) {
            throw new IOException("Not found nexcircleapp-firebase-adminsdk-fbsvc-2ed9490556.json trong resources/firebase");
        }

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        if(FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
            log.info("Firebase init success");
        } else {
            log.info("Firebase inited");
        }
    }


    public void sentNotification(NotificationFirebaseDto dto){
        UserDevice userDevice = this.userDeviceQueryService.findUserDeviceByUserId(dto.getUserId());
        this.notificationService.createNotification(dto);

        this.sendNotificationToOne(
                userDevice.getDeviceToken(),
                dto.getTitle(),
                dto.getBody(),
                dto.getData()
        );
    }

    private void sendNotificationToOne(String deviceToken, String title, String body, Object data) {

        try {
            // build DTO
            MessageFirebaseDto dto = MessageFirebaseDto.builder()
                    .token(deviceToken)
                    .notification(MessageFirebaseDto.Notification.builder()
                            .title(title)
                            .body(body)
                            .build())
                    .data(MessageFirebaseDto.Data.builder()
                            .payload(data != null ? objectMapper.writeValueAsString(data) : null)
                            .build())
                    .android(MessageFirebaseDto.Android.builder()
                            .priority("high")
                            .build())
                    .apns(MessageFirebaseDto.Apns.builder()
                            .headers(Map.of(
                                    "apns-collapse-id", "",
                                    "apns-priority", "10"
                            ))
                            .payload(MessageFirebaseDto.ApnsPayload.builder()
                                    .aps(MessageFirebaseDto.Aps.builder()
                                            .alert(MessageFirebaseDto.ApsAlert.builder()
                                                    .title(title)
                                                    .body(body)
                                                    .build())
                                            .sound("notification_sound.wav")
                                            .badge(1)
                                            .mutableContent(true)
                                            .contentAvailable(true)
                                            .build())
                                    .build())
                            .build())
                    .build();

            // map DTO -> Firebase Message
            Message message = mapToFirebaseMessage(dto);

            // send
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("Notification sent: {}", response);

        } catch (Exception e) {
            log.error("Error sending notification", e);
            throw new RuntimeException(e);
        }
    }

    private Message mapToFirebaseMessage(MessageFirebaseDto dto) {

        Message.Builder builder = Message.builder()
                .setToken(dto.getToken());

        // notification
        if (dto.getNotification() != null) {
            builder.setNotification(Notification.builder()
                    .setTitle(dto.getNotification().getTitle())
                    .setBody(dto.getNotification().getBody())
                    .build());
        }

        // data
        if (dto.getData() != null && dto.getData().getPayload() != null) {
            builder.putData("payload", dto.getData().getPayload());
        }

        // android
        builder.setAndroidConfig(AndroidConfig.builder()
                .setPriority(AndroidConfig.Priority.HIGH)
                .build());

        // apns
        if (dto.getApns() != null) {
            ApnsConfig.Builder apnsBuilder = ApnsConfig.builder();

            if (dto.getApns().getHeaders() != null) {
                dto.getApns().getHeaders().forEach(apnsBuilder::putHeader);
            }

            var apsDto = dto.getApns().getPayload().getAps();

            apnsBuilder.setAps(Aps.builder()
                    .setAlert(ApsAlert.builder()
                            .setTitle(apsDto.getAlert().getTitle())
                            .setBody(apsDto.getAlert().getBody())
                            .build())
                    .setSound(apsDto.getSound())
                    .setBadge(apsDto.getBadge())
                    .setMutableContent(apsDto.getMutableContent())
                    .setContentAvailable(apsDto.getContentAvailable())
                    .build());

            builder.setApnsConfig(apnsBuilder.build());
        }

        return builder.build();
    }
}
