package com.wyu;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wyu.entity.Notice;
import com.wyu.service.NoticeService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NoticeTest {
	@Autowired
	NoticeService noticeService;

	@Test
	public void testInsert() {
		for (int i = 0; i < 200; i++) {
			Notice notice = new Notice();
			notice.setNoticeName("标题" + i);
			notice.setContent("内容" + i);
			noticeService.insert(notice);
			System.gc();
		}
	}
}
