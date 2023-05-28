package com.jsrdxzw.springsecurityawesome.repository;

import com.jsrdxzw.springsecurityawesome.model.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xuzhiwei
 * @date 2023/5/18 22:58
 */
@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long> {
    Accounts findByCustomerId(int customerId);
}
