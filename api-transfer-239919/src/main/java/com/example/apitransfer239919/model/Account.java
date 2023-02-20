package com.example.apitransfer239919.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "owner_name", nullable = false)

    private String ownerName;
    @Column(name = "credit_card_number", nullable = false)
    private String creditCardNumber;
    @Column(name = "balance",length = 2, nullable = false)
    private BigDecimal balance;
    @Column(name = "currency", nullable = false)
    private String currency;
}
