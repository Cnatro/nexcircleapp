package com.nexcircle.shared.exception;

import com.nexcircle.shared.enums.MessageCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AppException extends RuntimeException{
    MessageCode messageCode;

    public AppException(MessageCode messageCode){
        super(messageCode.name()); // Truyền name của enum vào message của RuntimeException
        this.messageCode = messageCode;
    }

    /**
     * Đôi khi chúng ta cần ghi đè message để log chi tiết hơn
     * nhưng vẫn trả về MessageCode chung cho Client.
     */
    public AppException(MessageCode messageCode, String customMessage){
        super(customMessage);
        this.messageCode = messageCode;
    }
}
