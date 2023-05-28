package com.jsrdxzw.springsecurityawesome.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * @author xuzhiwei
 * @date 2023/5/18 22:55
 */
@Data
@Entity
@Table(name = "account_transactions")
public class AccountTransactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "account_number")
    private long accountNumber;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "transaction_dt")
    private Date transactionDt;

    @Column(name = "transaction_summary")
    private String transactionSummary;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "transaction_amt")
    private int transactionAmt;

    @Column(name = "closing_balance")
    private int closingBalance;

    @Column(name = "create_dt")
    private String createDt;
}
