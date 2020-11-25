package com.wyu.service;

import java.util.List;

import com.wyu.entity.BorrowInfo;

public abstract class BorrowInfoService {
	public abstract void approve(Integer id);

	public abstract void delete(Integer id);

	public abstract List<BorrowInfo> getBookShelf(Integer userId);

	public abstract List<BorrowInfo> getBorrowInfosByBookId(Integer bookId);

	public abstract List<BorrowInfo> getBorrowInfosPagination(Integer currentPage, Integer pageSize);

	public abstract List<BorrowInfo> getByBorrowStatesPagination(Integer states, Integer currentPage, Integer pageSize);

	public abstract int getCountByUserId(Integer id);

	public abstract List<BorrowInfo> getUserBorrowHistory(Integer userId, Integer currentPage, Integer pageSize);

	public abstract void insert(BorrowInfo borrowInfo) throws Exception;

	public abstract void refuse(Integer id);

	public abstract void renewBook(Integer id) throws Exception;

	public abstract void returnBook(Integer id);

	public abstract int getBorrowInfosCount();

	public abstract int getByBorrowStateCount(Integer states);

	public abstract int getByUserIdCount(Integer userId);

	public abstract BorrowInfo getById(Integer id);

	public abstract List<BorrowInfo> getByNameAndStatesPagination(String name, Integer states, Integer currentPage,
			Integer pageSize);

	public abstract int getByNameAndStatesCount(String name, Integer states);

	public abstract int numOfBorrowingToday();

	public abstract int numOfBorrowingThisMonth();

	public abstract int numOfBorrowingThisYear();

	public abstract int getOverDueCount();

	public abstract boolean bookIsBorrowing(Integer bookId);
}
