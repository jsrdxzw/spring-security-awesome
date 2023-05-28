package com.jsrdxzw.springsecurityawesome.controller;

import com.jsrdxzw.springsecurityawesome.model.Notice;
import com.jsrdxzw.springsecurityawesome.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiwei
 * @date 2023/5/14 14:29
 */
@RestController
public class NoticesController {
    @Autowired
    private NoticeRepository noticeRepository;

    @GetMapping("/notices")
    public ResponseEntity<Iterable<Notice>> getNotices() {
        Iterable<Notice> notices = noticeRepository.findAll();
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                .body(notices);
    }
}
