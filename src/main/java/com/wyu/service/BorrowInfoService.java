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
}
