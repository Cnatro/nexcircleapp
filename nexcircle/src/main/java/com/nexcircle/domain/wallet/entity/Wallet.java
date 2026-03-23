package com.nexcircle.domain.wallet.entity;

import com.nexcircle.domain.user.entity.User;
import com.nexcircle.infrastructure.persistence.user.jpa.UserJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Wallet {
    private UUID id;
    private User user;
    private BigDecimal balance;
    private String currency;
    private LocalDateTime updatedAt;

}