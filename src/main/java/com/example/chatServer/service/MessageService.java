package com.example.chatServer.service;

import com.example.chatServer.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private ChatMessageRepository messageRepository;

    public void saveMessage(MessageVo message) {
        String nextId = sequenceService.getNextSequenceId("message");
        message.setId(nextId); // 자동 증가된 id를 설정
        messageRepository.save(message);
    }
}
