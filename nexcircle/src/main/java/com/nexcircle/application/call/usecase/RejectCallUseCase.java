package com.nexcircle.application.call.usecase;

import com.nexcircle.domain.call.entity.CallSession;
import com.nexcircle.domain.call.repository.CallRepository;
import com.nexcircle.domain.user.service.SecurityContextService;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class RejectCallUseCase {
    SecurityContextService service;
    CallRepository callRepository;

    @Transactional
    public void execute(UUID sessionId) {
        UUID userId = service.getCurrentUserId();
        // Dùng hàm chung từ Repo
        CallSession session = callRepository.findAndVerifyParticipant(sessionId, userId);

        session.reject();
        callRepository.saveCallSession(session);

    }
}
