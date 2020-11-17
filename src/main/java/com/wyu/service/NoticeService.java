package com.wyu.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.wyu.dao.NoticeMapper;
import com.wyu.entity.Notice;

/**
 * 
 * @author 李达成
 * @since 2020/11/17
 */
public class NoticeService {
	@Autowired
	private NoticeMapper noticeMapper;

	/**
	 * @apiNote 增加一条公告
	 * @param name
	 * @param content
	 */
	public void addNotice(String noticeName, String content) {
		Notice notice = new Notice(null, noticeName, content, null);
		noticeMapper.insert(notice);
	}

}
