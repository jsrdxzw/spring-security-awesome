package com.jsrdxzw.springsecurityawesome.repository;

import com.jsrdxzw.springsecurityawesome.model.Notice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xuzhiwei
 * @date 2023/5/18 23:01
 */
@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {
}
