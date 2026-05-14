package com.nexcircle.domain.wallet.entity;

import com.nexcircle.domain.user.entity.User;
import com.nexcircle.infrastructure.persistence.user.jpa.UserJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Transaction {
    private UUID id;
    private User sender;
    private User receiver;
    private Wallet wallet;
    private BigDecimal amount;
    private String status;
    private String description;
    private String idempotencyKey;
    private LocalDateTime createdAt;
}