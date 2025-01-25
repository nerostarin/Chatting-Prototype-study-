package com.example.chatServer.controller;

import com.example.chatServer.service.ChatMessageRepository;
import com.example.chatServer.service.ChatService;
import com.example.chatServer.service.MessageService;
import com.example.chatServer.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class ChatController {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ChatService chatService;

    @GetMapping("/{issueId}")
    public Iterable<MessageVo> selectMessages(@PathVariable String issueId) {
        return chatMessageRepository.findByIssueId(Integer.parseInt(issueId));
    }

    @GetMapping("/read/{issueId}")
    public Iterable<MessageVo> readMessages(@PathVariable String issueId) {
        int userId = 4;
        String username = "명광호";
        return chatService.readChat(Integer.parseInt(issueId), userId, username);
    }
}
