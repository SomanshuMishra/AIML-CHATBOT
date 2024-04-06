package com.aimlpolestar.aimlPolestar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.ArrayList;


@Data
@Entity
public class SessionDetails {

    // Field
    @Id
    private String sessionId;
    private String crtUser;
    private ArrayList<UserFeedback> userFeedbacks = new ArrayList<>();
}
