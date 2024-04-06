package com.aimlpolestar.aimlPolestar.chat.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "session_details")
public class SessionDetails {

    @Id
    private String sessionId;
    private String crtUser;

    @OneToMany(mappedBy = "sessionDetails", cascade = CascadeType.ALL)
    private List<UserFeedback> userFeedbacks;


}
