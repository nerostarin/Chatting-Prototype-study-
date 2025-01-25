package com.example.chatServer.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Document(collection = "messages")
public class MessageVo {
    @JsonProperty("id")
    @Id
    private String id;

    @JsonProperty("userId")
    private int userId;

    @JsonProperty("issueId")
    private int issueId;

    @JsonProperty("sender")
    private String sender;

    @JsonProperty("content")
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    @JsonProperty("readBy")
    private Set<String> readBy = new HashSet<>();

    @JsonProperty("readById")
    private Set<String> readById = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Set<String> getReadBy() {
        return readBy;
    }

    public void setReadBy(Set<String> readBy) {
        this.readBy = readBy;
    }

    public Set<String> getReadById() {
        return readById;
    }

    public void setReadById(Set<String> readById) {
        this.readById = readById;
    }
}
