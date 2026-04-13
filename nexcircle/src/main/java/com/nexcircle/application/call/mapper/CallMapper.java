package com.nexcircle.application.call.mapper;

import com.nexcircle.application.call.dto.CallResponse;
import com.nexcircle.domain.call.entity.CallSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CallMapper {
    @Mapping(target = "callerName", source = "caller.fullName")
    @Mapping(target = "callerId", source = "caller.id")
    CallResponse toCallResponse(CallSession callSession);
}
