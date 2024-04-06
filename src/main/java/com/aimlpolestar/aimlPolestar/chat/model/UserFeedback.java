package com.aimlpolestar.aimlPolestar.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "user_feedback")
public class UserFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String answer;
    private boolean userRating;
}
