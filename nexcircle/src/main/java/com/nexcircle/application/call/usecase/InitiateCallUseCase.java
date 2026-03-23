package com.nexcircle.application.call.usecase;

import com.nexcircle.application.call.mapper.CallMapper;
import com.nexcircle.domain.call.repository.CallRepository;
import com.nexcircle.domain.user.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class InitiateCallUseCase {
    CallRepository callRepository;
    UserRepository userRepository;
    CallMapper callMapper;
}
