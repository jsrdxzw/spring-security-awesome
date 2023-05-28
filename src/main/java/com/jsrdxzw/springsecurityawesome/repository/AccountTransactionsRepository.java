package com.jsrdxzw.springsecurityawesome.repository;

import com.jsrdxzw.springsecurityawesome.model.AccountTransactions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xuzhiwei
 * @date 2023/5/18 22:55
 */
@Repository
public interface AccountTransactionsRepository extends CrudRepository<AccountTransactions, Long> {
    List<AccountTransactions> findByCustomerIdOrderByTransactionDtDesc(int customerId);
}
