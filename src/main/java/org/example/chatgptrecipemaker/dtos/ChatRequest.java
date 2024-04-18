package org.example.chatgptrecipemaker.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatRequest {
    private String model;
    private Integer n;
    private List<ChatResponse.Message> messages;
    private Integer temperature;
    private Integer maxTokens;
    private Boolean stream;
    private Integer presencePenalty;

    public void setMessages(List<Message> lstMessages) {
    }

    @Getter
    @Setter
    public static class Message {
        private String role;
        private String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }
}



