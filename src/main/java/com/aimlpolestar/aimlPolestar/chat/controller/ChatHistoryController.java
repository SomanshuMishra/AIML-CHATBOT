package com.aimlpolestar.aimlPolestar.controller;

import com.aimlpolestar.aimlPolestar.model.SessionDetails;
import com.aimlpolestar.aimlPolestar.services.ChatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatHistoryController {

    @Autowired
    private final ChatHistoryService chatHistoryService;

    public ChatHistoryController(ChatHistoryService chatHistoryService) {
        this.chatHistoryService = chatHistoryService;
    }


    @PostMapping("/chathistory")
    public ResponseEntity<String> saveChatHistory(@RequestBody SessionDetails sessionDetails) {
        try {
            chatHistoryService.saveChatHistory(sessionDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body("Chat history saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save the chat history");
        }
    }

}