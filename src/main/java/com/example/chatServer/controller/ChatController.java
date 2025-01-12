package com.example.chatServer.controller;

import com.example.chatServer.service.ChatMessageRepository;
import com.example.chatServer.service.MessageService;
import com.example.chatServer.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class ChatController {

    @Autowired
    private ChatMessageRepository chatMessageRepository;


    @GetMapping("/get")
    public Iterable<MessageVo> getMessages() {
        return chatMessageRepository.findAll();
    }

    @GetMapping("/{issueId}")
    public Iterable<MessageVo> selectMessages(@PathVariable int issueId) {
        return chatMessageRepository.findByIssueId(issueId);
    }
}
