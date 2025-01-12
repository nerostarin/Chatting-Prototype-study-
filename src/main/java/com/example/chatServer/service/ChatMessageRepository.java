package com.example.chatServer.service;

import com.example.chatServer.vo.MessageVo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<MessageVo, String> {
    List<MessageVo> findByIssueId(int issueId);
}
