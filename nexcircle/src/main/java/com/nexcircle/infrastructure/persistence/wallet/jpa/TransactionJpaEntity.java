package com.nexcircle.infrastructure.persistence.wallet.jpa;

import com.nexcircle.infrastructure.persistence.user.jpa.UserJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TransactionJpaEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserJpaEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private UserJpaEntity receiver;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private WalletJpaEntity walletJpaEntity;

    private BigDecimal amount;
    private String status;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(unique = true)
    private String idempotencyKey;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
        createdAt = LocalDateTime.now();
    }
}