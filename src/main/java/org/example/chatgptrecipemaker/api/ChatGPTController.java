package org.example.chatgptrecipemaker.api;

import org.example.chatgptrecipemaker.dtos.ChatResponse;
import org.example.chatgptrecipemaker.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatGPTController {

    private final ChatGPTService chatGPTService;

    @Autowired
    public ChatGPTController(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    @GetMapping("/chat")
    public List<ChatResponse.Choice> chatWithGPT(@RequestParam String message) {
        // Call the chatWithGPT method from ChatGPTService
        return chatGPTService.chatWithGPT(message);
    }
}
