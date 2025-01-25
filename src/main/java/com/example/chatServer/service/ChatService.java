package com.example.chatServer.service;

import com.example.chatServer.vo.MessageVo;

public interface ChatService {
    Iterable<MessageVo> readChat(int issueId, int userId, String userName);
}
