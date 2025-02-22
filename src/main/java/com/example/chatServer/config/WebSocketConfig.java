package com.example.chatServer.config;

import com.example.chatServer.service.ChatMessageRepository;
import com.example.chatServer.websocket.ChatWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatMessageRepository chatMessageRepository;

    // 생성자 주입을 통해 ChatMessageRepository를 주입받습니다.
    public WebSocketConfig(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // WebSocket 경로를 "/chat/{issueId}"로 변경
        registry.addHandler(new ChatWebSocketHandler(chatMessageRepository), "/chat/{issueId}")
                .setAllowedOrigins("*");  // 모든 출처에서의 연결을 허용
    }
}
