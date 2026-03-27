package com.nexcircle.infrastructure.scheduler;

import com.nexcircle.infrastructure.persistence.call.jpa.CallSessionJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
/// Xử lý cho video call
public class CallCleanupScheduler {
    private final CallSessionJpaRepository sessionRepository;
    // private final SignalingService signalingService; // Để báo cho FE tắt màn hình chờ

    @Scheduled(fixedRateString = "${app.call.cleanup-interval:60000}")
    @Transactional
    public void handleExpiredPendingCalls() {
        LocalDateTime timeoutThreshold = LocalDateTime.now().minusSeconds(45);

        // 1. Chỉ lấy IDs để xử lý hoặc dùng Update Query trực tiếp để tối ưu Performance
        // Thay vì lấy cả Entity lên rồi loop (O(n)), ta dùng 1 câu lệnh SQL duy nhất
        int updatedCount = sessionRepository.updateStatusForTimeoutCalls(
                "MISSED",
                "PENDING",
                timeoutThreshold,
                LocalDateTime.now()
        );

        if (updatedCount > 0) {
            log.info("System Cleanup: Marked {} calls as MISSED due to timeout", updatedCount);
            // 2. (Optional) Gửi tín hiệu Notify qua Socket để đôi bên tắt chuông
        }
    }
}