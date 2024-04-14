package com.devops.dockerization.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "bankAccounts")
public class BankAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "bank_account_no", nullable = false)
    private String bankAccountNo;

    @Column(name = "ifsc_code", nullable = false)
    private String ifscCode;
}
