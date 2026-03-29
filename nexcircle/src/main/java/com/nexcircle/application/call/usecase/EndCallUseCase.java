package com.nexcircle.application.call.usecase;

import com.nexcircle.domain.call.entity.CallSession;
import com.nexcircle.domain.call.repository.CallRepository;
import com.nexcircle.domain.user.service.SecurityContextService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EndCallUseCase {
    SecurityContextService service;
    CallRepository callRepository;

    @Transactional
    public void execute(UUID sessionId) {
        UUID userId = service.getCurrentUserId();
        CallSession session = callRepository.findAndVerifyParticipant(sessionId, userId);

        session.end(); // Logic Domain
        callRepository.updateParticipantStatus(sessionId, userId, null, LocalDateTime.now());
        callRepository.saveCallSession(session);

    }
}
