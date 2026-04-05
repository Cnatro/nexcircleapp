package com.nexcircle.infrastructure.websocket;

import com.nexcircle.application.call.dto.SignalMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class CallSignalingHandler extends TextWebSocketHandler {
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // Lưu session khi user connect (Frontend sẽ gửi userId qua query param)
        String userId = session.getUri().getQuery().split("=")[1];
        sessions.put(userId, session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        SignalMessage signal = objectMapper.readValue(message.getPayload(), SignalMessage.class);

        // Tìm người nhận và chuyển tiếp gói tin "Signature" WebRTC sang
        WebSocketSession target = sessions.get(signal.getToUserId());
        if (target != null && target.isOpen()) {
            target.sendMessage(new TextMessage(message.getPayload()));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.values().remove(session);
    }


}

/// //
//@Component
//@Slf4j
//public class CallSignalingHandler extends TextWebSocketHandler {
//
//    // Quản lý các kết nối đang online
//    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        // Lấy userId từ query param: ws://localhost:8080/ws/call?userId=123
//        String userId = getUserId(session);
//        if (userId != null) {
//            sessions.put(userId, session);
//            log.info("[WebSocket] User {} đã kết nối thành công", userId);
//        }
//    }
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        SignalMessage signal = objectMapper.readValue(message.getPayload(), SignalMessage.class);
//        log.debug("[WebSocket] Nhận tín hiệu {} từ {} cho {}", signal.getType(), signal.getFromUserId(), signal.getToUserId());
//
//        WebSocketSession targetSession = sessions.get(signal.getToUserId());
//        if (targetSession != null && targetSession.isOpen()) {
//            // Chuyển tiếp gói tin nguyên vẹn sang người nhận
//            targetSession.sendMessage(new TextMessage(message.getPayload()));
//        } else {
//            log.warn("[WebSocket] Người nhận {} không online hoặc đã ngắt kết nối", signal.getToUserId());
//        }
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
//        String userId = getUserId(session);
//        if (userId != null) {
//            sessions.remove(userId);
//            log.info("[WebSocket] User {} đã ngắt kết nối", userId);
//        }
//    }
//
//    private String getUserId(WebSocketSession session) {
//        String query = session.getUri().getQuery();
//        if (query != null && query.contains("userId=")) {
//            return query.split("userId=")[1];
//        }
//        return null;
//    }
//
//    // Hàm hỗ trợ để các UseCase gọi từ bên ngoài
//    public void sendSignal(String toUserId, SignalMessage signal) {
//        WebSocketSession session = sessions.get(toUserId);
//        try {
//            if (session != null && session.isOpen()) {
//                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(signal)));
//            }
//        } catch (Exception e) {
//            log.error("[WebSocket] Lỗi gửi tín hiệu: {}", e.getMessage());
//        }
//    }
//}
