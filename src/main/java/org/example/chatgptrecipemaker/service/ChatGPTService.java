package org.example.chatgptrecipemaker.service;

import org.example.chatgptrecipemaker.dtos.ChatRequest;
import org.example.chatgptrecipemaker.dtos.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ChatGPTService {

    private final WebClient webClient;
    private final String openaiKey;

    public ChatGPTService(WebClient.Builder webClientBuilder,
                          @Value("${app.api-key}") String openaiKey) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1/chat/completions").build();
        this.openaiKey = openaiKey;
    }

    public List<ChatResponse.Choice> chatWithGPT(String message) {
        // Create a list of messages
        List<ChatRequest.Message> lstMessages = Arrays.asList(
                new ChatRequest.Message("system", "You are a recipe provider based on provided ingredients."),
                new ChatRequest.Message("user", message)
        );

        // Create a ChatRequest object
        ChatRequest chatRequest = new ChatRequest();
        chatRequest.setModel("gpt-3.5-turbo");
        chatRequest.setN(5);
        chatRequest.setTemperature(1);
        chatRequest.setMaxTokens(1000);
        chatRequest.setStream(false);
        chatRequest.setPresencePenalty(2);
        chatRequest.setMessages(lstMessages);

        // Send the request to the API
        ChatResponse response = webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/chat/completions").build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + openaiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(chatRequest))
                .retrieve()
                .bodyToMono(ChatResponse.class)
                .block();

        if (response != null) {
            return response.getChoices();
        } else {
            return new ArrayList<>(); // or handle null response as needed
        }
    }

}

