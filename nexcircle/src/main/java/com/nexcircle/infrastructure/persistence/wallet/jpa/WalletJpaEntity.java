package com.nexcircle.infrastructure.persistence.wallet.jpa;

import com.nexcircle.infrastructure.persistence.user.jpa.UserJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "wallets")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class WalletJpaEntity {

    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private UserJpaEntity user;

    private BigDecimal balance;
    private String currency;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
        if (balance == null) balance = BigDecimal.ZERO;
        if (currency == null) currency = "VND";
        updatedAt = LocalDateTime.now();
    }
}