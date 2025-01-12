package com.example.chatServer.websocket;

import com.example.chatServer.service.ChatMessageRepository;
import com.example.chatServer.service.MessageService;
import com.example.chatServer.vo.MessageVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.time.LocalDateTime;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final ChatMessageRepository chatMessageRepository;
    private final ObjectMapper objectMapper;

    public ChatWebSocketHandler(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.objectMapper = new ObjectMapper();

        // JavaTimeModule 등록
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("------------------------------------------------");
        System.out.println("받은 메시지 : " + payload);
        System.out.println("------------------------------------------------");

        // JSON -> MessageVo 객체 변환
        MessageVo messageVo = objectMapper.readValue(payload, MessageVo.class);

        // 서버에서 받은 메시지에 현재 시간 추가 (필요시)
        if (messageVo.getTimestamp() == null) {
            messageVo.setTimestamp(LocalDateTime.now()); // 메시지 시간 설정
        }

        // 서비스로 메시지 저장 요청
        chatMessageRepository.save(messageVo);

        // 연결된 클라이언트들에게 메시지 브로드캐스트
        session.sendMessage(new TextMessage("Message saved: " + payload));
    }
}
