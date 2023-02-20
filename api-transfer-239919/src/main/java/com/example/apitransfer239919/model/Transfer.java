package com.example.apitransfer239919.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="transfers")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "currency", nullable = false)
    private String currency;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="source_account_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_SOURCE_ACCOUNT_ID"))

    private Account sourceAccount;
    @ManyToOne
    @JoinColumn(name="destination_account_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_DESTINATION_ACCOUNT_ID"))

    private Account destinationAccount;





}
