package com.nexcircle.application.call.usecase;

import com.nexcircle.application.call.dto.CallResponse;
import com.nexcircle.application.call.mapper.CallMapper;
import com.nexcircle.domain.call.repository.CallRepository;
import com.nexcircle.domain.user.service.SecurityContextService;
import com.nexcircle.shared.enums.MessageCode;
import com.nexcircle.shared.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AcceptCallUseCase {
    CallRepository callRepository;
    SecurityContextService service;
    CallMapper mapper;

    @Transactional
    public CallResponse execute(UUID sessionId){
        var userId = service.getCurrentUserId();
        var session = callRepository.findCallSessionById(sessionId)
                .orElseThrow(() -> new AppException(MessageCode.CALL_NOT_FOUND));

        boolean isInvited = callRepository.isParticipant(sessionId, userId);
        if (!isInvited) {
            // Ngăn chặn trường hợp User A bắt máy cuộc gọi của User B
            throw new AppException(MessageCode.FAIL, "You are not a participant of this call");
        }

        session.accept();
        callRepository.updateParticipantStatus(sessionId, userId, LocalDateTime.now(), null);
        callRepository.saveCallSession(session);//cap nhat trang thai accept

        return mapper.toCallResponse(session);
    }
}
