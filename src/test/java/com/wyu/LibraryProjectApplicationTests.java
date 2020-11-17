package com.wyu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wyu.dao.BorrowInfoDao;
import com.wyu.dao.NoticeDao;
import com.wyu.entity.Notice;

@SpringBootTest
class LibraryProjectApplicationTests {

	@Autowired
	BorrowInfoDao borrowInfoDao;

	@Autowired
	NoticeDao noticeDao;

	@Test
	void contextLoads() {
	}

	@Test
	void testBorrowInfoDao() {
		System.out.println(borrowInfoDao.getById(1));
		System.out.println(borrowInfoDao.getOverDueInfo().toString());
		System.out.println(borrowInfoDao.list().toString());
	}

	@Test
	void testNoticeDao() {
		Notice notice = new Notice(null, "这是个标题", "这是内容", null);
		noticeDao.insert(notice);
		System.out.println(noticeDao.getById(1));
		noticeDao.deleteById(1);
	}
}
