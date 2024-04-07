package com.aimlpolestar.aimlPolestar.WrapPdf.controller;

import com.aimlpolestar.aimlPolestar.WrapPdf.model.PdfModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ProcessPdfController {
    @Value("${external.api.process_pdf_url}")
    private String externalApiUrl;

    @PostMapping("/process_pdf")
    public ResponseEntity<String> wrapProcessPdf(@RequestBody PdfModel pdfModel) {
        // Manually serialize PdfModel to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest;
        try {
            jsonRequest = objectMapper.writeValueAsString(pdfModel);
        } catch (JsonProcessingException e) {
            // Handle serialization error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error serializing PdfModel to JSON");
        }

        // Create HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create HTTP entity with the JSON request body and headers
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonRequest, headers);

        // Make a request to the external API
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                externalApiUrl,
                HttpMethod.POST,
                requestEntity,
                String.class);

        return responseEntity;
    }
}