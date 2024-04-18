package org.example.chatgptrecipemaker.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ChatResponse {
    private String id;
    private String object;
    private Integer created;
    private String model;
    private List<Choice> choices;
    private Usage usage;

    public List<Choice> getChoices() {
        return null;
    }

    public static class Choice {
        private Integer index;
        private ChatRequest.Message message;
        private String finishReason;
    }

    @Getter
    @Setter
    public static class Message {
        private String role;
        private String content;
    }


    @Getter
    @Setter
    public static class Usage {
        private int prompt_tokens;
        private int completion_tokens;
        private int total_tokens;
    }
}