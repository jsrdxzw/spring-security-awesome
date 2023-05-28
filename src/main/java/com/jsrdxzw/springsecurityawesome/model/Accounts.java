package com.jsrdxzw.springsecurityawesome.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author xuzhiwei
 * @date 2023/5/18 22:18
 */
@Data
@Entity
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "create_dt")
    private String createDt;
}
