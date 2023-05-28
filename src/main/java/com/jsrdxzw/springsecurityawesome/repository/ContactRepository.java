package com.jsrdxzw.springsecurityawesome.repository;

import com.jsrdxzw.springsecurityawesome.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xuzhiwei
 * @date 2023/5/18 22:59
 */
@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
}
