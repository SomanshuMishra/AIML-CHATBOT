package com.aimlpolestar.aimlPolestar.WrapPdf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class GenerateTextService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${external.api.get_answer_url}")
    private String externalApiUrl;

    private static final String QA_FILE_PATH = "question_answer.txt";
    private Map<String, String> questionAnswerMap;
    private String userName; // To store user's name

    public GenerateTextService() {
        loadQuestionAnswerMap();
    }

    public String getAnswer(String inputQuery) {
        // Check if the input query is a command to remember the user's name
        if (inputQuery.toLowerCase().startsWith("remember my name is ")) {
            // Extract the name and store it
            String name = inputQuery.substring("remember my name is ".length()).trim();
            userName = name; // Update userName
            return "Got it! I'll remember your name as " + userName + ".";
        }

        // Check if the input query is a request for the user's name
        if (inputQuery.equalsIgnoreCase("what's my name")) {
            // If the name is remembered, return it; otherwise, ask the user to provide it
            return (userName != null) ? "Your name is " + userName + "." : "I don't know your name yet.";
        }

        // Check if the answer exists in the map
        String answer = questionAnswerMap.get(inputQuery);
        if (answer != null) {
            return answer;
        }

        // If not found, query the external API
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(inputQuery, headers);
        answer = restTemplate.postForObject(externalApiUrl, request, String.class);

        // Save question and answer to the file
        saveToTextFile(inputQuery, answer);

        return answer;
    }

    private void loadQuestionAnswerMap() {
        questionAnswerMap = new HashMap<>();
        try {
            File file = new File(QA_FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("::");
                    if (parts.length == 2) {
                        questionAnswerMap.put(parts[0], parts[1]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToTextFile(String question, String answer) {
        try (FileWriter writer = new FileWriter(QA_FILE_PATH, true)) {
            writer.append(question)
                    .append("::")
                    .append(answer)
                    .append("\n");
            questionAnswerMap.put(question, answer); // Update map
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



