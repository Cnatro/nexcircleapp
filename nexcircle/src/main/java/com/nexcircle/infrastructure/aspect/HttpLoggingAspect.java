package com.nexcircle.infrastructure.aspect;

import com.nexcircle.domain.user.service.SecurityContextService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class HttpLoggingAspect {

    private final SecurityContextService securityContextService;
    
    @Around("execution(* com.nexcircle.presentation.controller..*(..))")
    public Object logControllerActions(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        // Lấy User ID để biết "ai" đang gọi API
        String userId;
        try {
            userId = securityContextService.getCurrentUserId().toString();
        } catch (Exception e) {
            userId = "ANONYMOUS"; // Trường hợp chưa login (nếu có API public)
        }

        log.info("==> [API CALL] User: {} | Action: {}.{} | Params: {}",
                userId, className, methodName, Arrays.toString(args));

        try {
            Object result = joinPoint.proceed(); // Thực thi Controller

            long duration = System.currentTimeMillis() - start;
            log.info("<== [API RESPONSE] User: {} | Action: {}.{} | Duration: {}ms",
                    userId, className, methodName, duration);

            return result;
        } catch (Throwable ex) {
            log.error("!!! [API ERROR] User: {} | Action: {}.{} | Message: {}",
                    userId, className, methodName, ex.getMessage());
            throw ex; // Ném tiếp để GlobalExceptionHandler xử lý
        }
    }
}