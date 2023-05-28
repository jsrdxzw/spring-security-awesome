package com.jsrdxzw.springsecurityawesome.controller;

import com.jsrdxzw.springsecurityawesome.model.Loans;
import com.jsrdxzw.springsecurityawesome.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xuzhiwei
 * @date 2023/5/14 13:27
 */
@RestController
public class LoansController {
    @Autowired
    private LoanRepository loanRepository;

    @GetMapping("/myLoans")
    public List<Loans> getLoanDetails(@RequestParam int id) {
        return loanRepository.findByCustomerIdOrderByStartDtDesc(id);
    }
}
