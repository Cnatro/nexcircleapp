//package com.nexcircle.infrastructure.websocket;
//
//import com.nexcircle.infrastructure.security.jwt.JwtTokenProvider;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
//
//import java.util.Map;
//
//@RequiredArgsConstructor
//public class WebSocketAuthInterceptor extends HttpSessionHandshakeInterceptor {
//    private final JwtTokenProvider tokenProvider;
//
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
//                                   WebSocketHandler wsHandler, Map<String, Object> attributes) {
//        // Lấy token từ query param ?token=...
//        String query = request.getURI().getQuery();
//        if (query != null && query.contains("token=")) {
//            String token = query.split("token=")[1].split("&")[0];
//            if (tokenProvider.validateToken(token)) {
//                return true; // Cho phép kết nối
//            }
//        }
//        return false; // Từ chối kết nối
//    }
//}
