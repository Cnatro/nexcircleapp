package com.nexcircle.application.call.usecase;

import com.nexcircle.domain.call.entity.CallSession;
import com.nexcircle.domain.call.repository.CallRepository;
import com.nexcircle.domain.user.entity.User;
import com.nexcircle.domain.user.service.SecurityContextService;
import com.nexcircle.shared.enums.MessageCode;
import com.nexcircle.shared.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CancelCallUseCase {
    CallRepository callRepository;
    SecurityContextService service;

    @Transactional
    public void execute(UUID sessionId){
        UUID userId = service.getCurrentUserId();
        CallSession session = callRepository.findCallSessionById(sessionId)
                .orElseThrow(() -> new AppException(MessageCode.CALL_NOT_FOUND));
        if(!session.getCaller().getId().equals(userId))
            throw new AppException(MessageCode.UNAUTHORIZED, "User not permission access");

        session.cancel();
        callRepository.saveCallSession(session);
    }
}
