package com.nexcircle.infrastructure.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexcircle.application.call.dto.SignalMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class CallSignalingHandler extends TextWebSocketHandler {

    // ✅ Dùng UUID cho chuẩn
    private final Map<UUID, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String userIdStr = getUserId(session);
        if (userIdStr != null) {
            try {
                UUID userId = UUID.fromString(userIdStr);
                sessions.put(userId, session);
                log.info("[WebSocket] User {} connected", userId);
            } catch (Exception e) {
                log.error("[WebSocket] Invalid userId format: {}", userIdStr);
            }
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        try {
            SignalMessage signal = objectMapper.readValue(message.getPayload(), SignalMessage.class);

            if (signal.getToUserId() == null) {
                log.warn("[WebSocket] Missing toUserId");
                return;
            }

            log.debug("[WebSocket] {} from {} -> {}",
                    signal.getType(),
                    signal.getFromUserId(),
                    signal.getToUserId());

            WebSocketSession targetSession = sessions.get(signal.getToUserId());

            if (targetSession != null && targetSession.isOpen()) {
                targetSession.sendMessage(new TextMessage(message.getPayload()));
            } else {
                log.warn("[WebSocket] User {} not online", signal.getToUserId());
            }

        } catch (Exception e) {
            log.error("[WebSocket] Error handling message: {}", e.getMessage());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        UUID disconnectedUser = null;

        for (Map.Entry<UUID, WebSocketSession> entry : sessions.entrySet()) {
            if (entry.getValue().equals(session)) {
                disconnectedUser = entry.getKey();
                break;
            }
        }

        if (disconnectedUser != null) {
            sessions.remove(disconnectedUser);
            log.info("[WebSocket] User {} disconnected", disconnectedUser);
        }
    }

    // ✅ Parse query chuẩn
    private String getUserId(WebSocketSession session) {
        String query = session.getUri().getQuery();
        if (query == null) return null;

        for (String param : query.split("&")) {
            if (param.startsWith("userId=")) {
                return param.split("=")[1];
            }
        }
        return null;
    }

    // ✅ UseCase gọi vào đây
    public void sendSignal(UUID toUserId, SignalMessage signal) {
        WebSocketSession session = sessions.get(toUserId);

        try {
            if (session != null && session.isOpen()) {
                String json = objectMapper.writeValueAsString(signal);
                session.sendMessage(new TextMessage(json));
            } else {
                log.warn("[WebSocket] Cannot send, user {} not online", toUserId);
            }
        } catch (Exception e) {
            log.error("[WebSocket] Send error: {}", e.getMessage());
        }
    }
}