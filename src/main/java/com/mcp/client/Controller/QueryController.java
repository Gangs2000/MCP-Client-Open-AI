package com.mcp.client.Controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/mcp")
public class QueryController {

    private ChatClient chatClient;

    public QueryController(ChatClient.Builder builder, ToolCallbackProvider toolCallbackProvider) {
        this.chatClient = builder
                .defaultToolCallbacks(toolCallbackProvider)
                .build();
    }

    @GetMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestParam(value = "query", defaultValue = "Get current weather in Madurai city") String query) {
        return ResponseEntity.ok(chatClient.prompt(query).call().content());
    }
}
