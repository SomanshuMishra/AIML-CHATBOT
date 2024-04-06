package com.aimlpolestar.aimlPolestar.services;

import com.aimlpolestar.aimlPolestar.model.SessionDetails;
import com.aimlpolestar.aimlPolestar.repository.ChatHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;


@Service
public class ChatHistoryService {

    @Autowired
    private final ChatHistoryRepo chatHistoryRepo;

    public ChatHistoryService(ChatHistoryRepo chatHistoryRepo) {
        this.chatHistoryRepo = chatHistoryRepo;
    }

    //function to save chat history
    public void saveChatHistory(SessionDetails sessionDetails) throws IOException {
        chatHistoryRepo.save(sessionDetails);
    }
}