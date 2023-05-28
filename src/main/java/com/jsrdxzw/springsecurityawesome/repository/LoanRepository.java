package com.jsrdxzw.springsecurityawesome.repository;

import com.jsrdxzw.springsecurityawesome.model.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xuzhiwei
 * @date 2023/5/18 23:00
 */
@Repository
public interface LoanRepository extends CrudRepository<Loans, Long> {
    List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);
}
