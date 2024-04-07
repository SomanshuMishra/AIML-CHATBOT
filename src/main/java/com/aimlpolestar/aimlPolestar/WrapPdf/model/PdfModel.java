package com.aimlpolestar.aimlPolestar.WrapPdf.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;



@Data
public class PdfModel {
    private String input_query;
    private String user_question;
}
