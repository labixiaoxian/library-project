package com.wyu.service;

import java.util.List;

import com.wyu.entity.Notice;

/**
 * 
 * @author 李达成
 * @since 2020/11/18
 *
 */
public abstract class NoticeService {
	public abstract void delete(Integer id);

	public abstract List<Notice> fuzzyQuery(String name);

	public abstract List<Notice> fuzzyQueryPagination(String name, int currentPage, int size);

	public abstract Notice get(Integer id);

	public abstract Notice getLatestNotice();

	public abstract void insert(Notice notice);

	public abstract List<Notice> queryAll();

	public abstract List<Notice> queryAllPagination(int currentPage, int size);

	public abstract Notice update(Notice notice);

	public abstract int fuzzyQueryBynameCount(String name);

	public abstract int listCount();
}
