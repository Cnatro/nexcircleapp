package com.nexcircle.infrastructure.persistence.wallet.jpa;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "wallet_ledger")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class WalletLedgerJpaEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private WalletJpaEntity walletJpaEntity;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private TransactionJpaEntity transactionJpaEntity;

    private BigDecimal amountChange;
    private BigDecimal balanceBefore;
    private BigDecimal balanceAfter;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
        createdAt = LocalDateTime.now();
    }
}