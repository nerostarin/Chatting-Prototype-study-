package com.example.chatServer.config;

import com.example.chatServer.service.ChatMessageRepository;
import com.example.chatServer.websocket.ChatWebSocketHandler;
import com.example.chatServer.service.MessageService;  // MessageService import
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatMessageRepository chatMessageRepository;

    // 생성자 주입을 통해 MessageService를 주입받습니다.
    public WebSocketConfig(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // ChatWebSocketHandler에 MessageService를 주입하여 사용
        registry.addHandler(new ChatWebSocketHandler(chatMessageRepository), "/chat")
                .setAllowedOrigins("*");
    }
}
