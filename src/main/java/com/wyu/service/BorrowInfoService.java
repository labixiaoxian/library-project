package com.wyu.service;

import java.util.List;

import com.wyu.entity.BorrowInfo;

public abstract class BorrowInfoService {
	public abstract List<BorrowInfo> getBookShelf(Integer userId);

	public abstract List<BorrowInfo> getUserBorrowHistory(Integer userId, Integer currentPage, Integer pageSize);

	public abstract List<BorrowInfo> getReviewingBorrowInfos(Integer currentPage, Integer pageSize);

	public abstract List<BorrowInfo> getBorrowingBorrowInfos(Integer currentPage, Integer pageSize);

	public abstract List<BorrowInfo> getFinishedBorrowInfos(Integer currentPage, Integer pageSize);

	public abstract void approve(Integer id);

	public abstract void returnBook(Integer id);

	public abstract void renewBook(Integer id);

	public abstract int getCountByUserId(Integer id);

	public abstract void insert(BorrowInfo borrowInfo) throws Exception;

	public abstract List<BorrowInfo> getBorrowInfosPagination(Integer currentPage, Integer pageSize);
}
