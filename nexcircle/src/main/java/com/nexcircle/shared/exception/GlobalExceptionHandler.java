package com.nexcircle.shared.exception;

import com.nexcircle.shared.dto.ApiResponse;
import com.nexcircle.shared.enums.MessageCode;
import com.nexcircle.shared.utils.ResponseFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // Handle Validation Exceptions (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex){
        return ResponseEntity.badRequest().body(ResponseFactory.error(MessageCode.INVALID_INPUT));
    }

    //Handle business logic
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Void>> handleAppException(AppException ex){
        log.warn("Business logic violation: {} - Details: {}", ex.getMessageCode(), ex.getMessage());
        return ResponseEntity.badRequest().body(ResponseFactory.error(ex.getMessageCode()));
    }

    //Handle All Other Exceptions (System Errors)
    //Đảm bảo không lộ StackTrace ra Client vì lý do bảo mật.
    @ExceptionHandler(Exception.class)
    public  ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex){
        log.error("Uncaught exception occurred: ", ex);
        return ResponseEntity.internalServerError().body(ResponseFactory.error(MessageCode.INTERNAL_SERVER_ERROR));
    }

    //Catch exception when both people click accept and cancle
    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ApiResponse<Void>> handleOptimisticLockingFailure(ObjectOptimisticLockingFailureException ex) {
        log.warn("Conflict detected: Call state was updated by another user.");
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ResponseFactory.error(MessageCode.CALL_FAILED)); // Hoặc một code ACTION_CONFLICT
    }
}
