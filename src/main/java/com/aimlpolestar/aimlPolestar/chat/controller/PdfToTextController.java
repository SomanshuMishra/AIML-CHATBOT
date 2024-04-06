package com.aimlpolestar.aimlPolestar.chat.controller;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/pdf")
public class PdfToTextController {
	
    private static final Logger logger = LoggerFactory.getLogger(PdfToTextController.class);


    @PostMapping("/convert")
    public ResponseEntity<String> convertPdfToText(@RequestParam("file") MultipartFile file) {
        try {
            // Load the PDF document
            try (PDDocument document = PDDocument.load(file.getInputStream())) {
                // Create PDFTextStripper instance
                PDFTextStripper stripper = new PDFTextStripper();

                // Get text content from the PDF
                String text = stripper.getText(document);

                // Return the extracted text in JSON format
                return ResponseEntity.ok("{\"text\": \"" + text + "\"}");
            }
        } catch (IOException e) {
            // Return an error response if there's an error reading the PDF or extracting text
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error processing PDF content\"}");
        }
    }
}
