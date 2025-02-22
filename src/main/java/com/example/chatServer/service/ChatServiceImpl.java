package com.example.chatServer.service;

import com.example.chatServer.vo.MessageVo;
import com.example.chatServer.websocket.ChatWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService{

    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public ChatServiceImpl(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @Override
    public Iterable<MessageVo> readChat(int issueId, int userId, String userName) {
        Iterable<MessageVo> messages = chatMessageRepository.findByIssueId(issueId);
        Iterable<MessageVo> updateMessages;
        List<MessageVo> saveUpdateMessages = new ArrayList<>();
        for (MessageVo message : messages) {
            // 본인이 쓴 글은 패스
            if (message.getUserId() == userId) {
                System.out.println("작성자는 읽을 수 없습니다: " + message.getId());
                continue;
            }

            if (message.getReadById() != null && message.getReadById().contains(userId)) {
                // 이미 읽은 메시지는 패스
                System.out.println("이미 읽은 메시지: " + message.getId());
                continue;
            }

            if (message.getReadById() == null) {
                message.setReadById(new HashSet<>());
            }
            if (message.getReadBy() == null) {
                message.setReadBy(new HashSet<>());
            }

            // 본인 아이디를 읽음 목록에 추가
            message.getReadById().add(String.valueOf(userId));
            // 본인 이름을 읽음 목록에 추가
            message.getReadBy().add(userName);
            // 메시지 업데이트
            chatMessageRepository.save(message);

            saveUpdateMessages.add(message);
        }

        updateMessages = saveUpdateMessages;

        return updateMessages;
    }
}
