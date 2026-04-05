package com.nexcircle.application.call.usecase;

import com.nexcircle.application.call.dto.CallRequest;
import com.nexcircle.application.call.dto.CallResponse;
import com.nexcircle.application.call.dto.SignalMessage;
import com.nexcircle.application.call.mapper.CallMapper;
import com.nexcircle.domain.call.entity.CallParticipant;
import com.nexcircle.domain.call.entity.CallSession;
import com.nexcircle.domain.call.repository.CallRepository;
import com.nexcircle.domain.user.entity.User;
import com.nexcircle.domain.user.repository.UserRepository;
import com.nexcircle.domain.user.service.SecurityContextService;
import com.nexcircle.shared.enums.MessageCode;
import com.nexcircle.shared.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class InitiateCallUseCase {
    CallRepository callRepository;
    UserRepository userRepository;
    CallMapper callMapper;
    SecurityContextService service;

    @Transactional
    public CallResponse execute(CallRequest request){
        UUID callerId = service.getCurrentUserId();
        User caller = userRepository.findById(callerId)
                .orElseThrow(() -> new AppException(MessageCode.USER_NOT_FOUND));
        User receiver = userRepository.findById((request.getReceiverId()))
                .orElseThrow(() -> new AppException(MessageCode.USER_NOT_FOUND));

        CallSession session = CallSession.createPending(caller, request.getType());
        CallSession savedSession = callRepository.saveCallSession(session);

        CallParticipant participant = CallParticipant.builder()
                .id(UUID.randomUUID())
                .callSession(savedSession)
                .user(receiver)
                .build();
        callRepository.saveParticipant(participant);

//        SignalMessage ringSignal = SignalMessage.builder()
//                .type("INCOMING_CALL")
//                .fromUserId(session.getCaller().getId().toString())
//                .toUserId(request.getReceiverId())
//                .sessionId(session.getId())
//                .build();
//
//        signalingHandler.sendSignal(request.getReceiverId(), ringSignal);

        return callMapper.toCallResponse(savedSession);
    }
}
