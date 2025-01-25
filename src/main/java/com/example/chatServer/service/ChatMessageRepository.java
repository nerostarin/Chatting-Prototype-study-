package com.example.chatServer.service;

import com.example.chatServer.vo.MessageVo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<MessageVo, String> {
    @Query("{ 'issueId': ?0 }")
    Iterable<MessageVo> findByIssueId(int issueId);
}
