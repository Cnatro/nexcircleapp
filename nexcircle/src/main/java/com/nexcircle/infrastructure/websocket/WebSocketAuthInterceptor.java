package com.nexcircle.infrastructure.websocket;

import com.nexcircle.domain.user.service.SecurityContextService;
import com.nexcircle.infrastructure.security.jwt.JwtTokenProvider;
import com.nexcircle.infrastructure.security.user.UserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class WebSocketAuthInterceptor extends HttpSessionHandshakeInterceptor {
    private final JwtTokenProvider tokenProvider;
//    private final SecurityContextService service;
    private  final  UserDetails userDetails;
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) {
        // Lấy token từ query param ?token=...
        String query = request.getURI().getQuery();
        if (query != null && query.contains("token=")) {
            // Logic tách token từ query string
            String token = query.split("token=")[1].split("&")[0];

            if (tokenProvider.validateTokenOnly(token)) {
                // Senior Tip: Trích xuất username và lưu vào attributes để Handler có thể dùng lại
                String username = tokenProvider.extractUsername(token);
                attributes.put("username", username);
                return true;
            }
        }
        log.warn("WebSocket Handshake bị từ chối do Token không hợp lệ");
        return false;
    }
}
