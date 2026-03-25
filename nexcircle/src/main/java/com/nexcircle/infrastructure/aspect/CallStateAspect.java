package com.nexcircle.infrastructure.aspect;

import com.nexcircle.domain.call.entity.CallSession;
import com.nexcircle.domain.call.repository.CallRepository;
import com.nexcircle.shared.enums.MessageCode;
import com.nexcircle.shared.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
@RequiredArgsConstructor
public class CallStateAspect {
    private final CallRepository callRepository;

    @Before("@annotation(checkCallState) && args(sessionId,..)")
    public void validateCallIsActive(UUID sessionId) {
        CallSession session = callRepository.findCallSessionById(sessionId)
                .orElseThrow(() -> new AppException( MessageCode.CALL_NOT_FOUND));

        if ("ENDED".equals(session.getStatus()) || "REJECTED".equals(session.getStatus())) {
            throw new AppException(MessageCode.FAIL, "Call is no longer active");
        }
    }
}