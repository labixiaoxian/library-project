package com.wyu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wyu.service.NoticeService;

@SpringBootTest
public class NoticeTest {
    @Autowired
    NoticeService noticeService;

    @Test
    public void testInsert() {
        for (int i = 0; i < 100; i++) {
            noticeService.insert("标题" + i, "内容" + i);
        }
    }
}
