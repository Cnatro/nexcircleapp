package com.nexcircle.application.call.usecase;

import com.nexcircle.application.call.dto.SignalMessage;
import com.nexcircle.domain.call.entity.CallSession;
import com.nexcircle.domain.call.repository.CallRepository;
import com.nexcircle.domain.user.service.SecurityContextService;
import com.nexcircle.infrastructure.websocket.CallSignalingHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RejectCallUseCase {
    SecurityContextService service;
    CallRepository callRepository;
    CallSignalingHandler signalingHandler;

    @Transactional
    public void execute(UUID sessionId) {
        UUID userId = service.getCurrentUserId();
        // Dùng hàm chung từ Repo
        CallSession session = callRepository.findAndVerifyParticipant(sessionId, userId);

        session.reject();
        callRepository.saveCallSession(session);

//        SignalMessage rejectSignal = SignalMessage.builder()
//                .type("REJECT_CALL")
//                .fromUserId(userId.toString())
//                .toUserId(session.getCaller().getId())
//                .sessionId(session.getId())
//                .build();
//
//        signalingHandler.sendSignal(session.getCaller().getId(), rejectSignal);

        UUID otherUserId = callRepository.findOtherParticipant(sessionId, userId);

        SignalMessage rejectSignal = SignalMessage.builder()
                .type("REJECT_CALL")
                .fromUserId(userId.toString())
                .toUserId(otherUserId)
                .sessionId(session.getId())
                .build();

        signalingHandler.sendSignal(otherUserId, rejectSignal);

    }
}
