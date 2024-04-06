package com.aimlpolestar.aimlPolestar.chat.repository;

import com.aimlpolestar.aimlPolestar.chat.model.SessionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatHistoryRepo extends JpaRepository<SessionDetails, String> {

}
