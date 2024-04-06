package com.edem.bankingapp.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_holder_name")
    private String accountHolderName;

    private double balance;

    private ZonedDateTime createdAt;
    private AccountType accountType;

    public Account(Long id, String accountHolderName, double balance, AccountType accountType) {
        this.id = id;
        this.accountType = accountType;
        this.accountHolderName =   accountHolderName;
        this.balance = balance;
    }
}
