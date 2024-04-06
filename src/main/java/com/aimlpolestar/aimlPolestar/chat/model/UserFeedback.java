package com.aimlpolestar.aimlPolestar.chat.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "chatHistory")
public class UserFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String answer;
    private boolean userRating;

    @ManyToOne
    @JoinColumn(name = "session_id") // Use the correct column name here
    private SessionDetails sessionDetails;
}
