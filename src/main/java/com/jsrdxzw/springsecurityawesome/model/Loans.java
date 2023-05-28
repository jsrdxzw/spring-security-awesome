package com.jsrdxzw.springsecurityawesome.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * @author xuzhiwei
 * @date 2023/5/18 22:53
 */
@Data
@Entity
@Table(name="loans")
public class Loans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanNumber;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name="start_dt")
    private Date startDt;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "total_loan")
    private int totalLoan;

    @Column(name = "amount_paid")
    private int amountPaid;

    @Column(name = "outstanding_amount")
    private int outstandingAmount;

    @Column(name = "create_dt")
    private String createDt;
}
