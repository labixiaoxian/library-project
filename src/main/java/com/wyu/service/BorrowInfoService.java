package com.wyu.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wyu.dao.BookMapper;
import com.wyu.dao.BorrowInfoMapper;
import com.wyu.dao.UserInfoMapper;
import com.wyu.dao.UserMapper;
import com.wyu.entity.BorrowInfo;

public abstract class BorrowInfoService {

	@Autowired
	BorrowInfoMapper borrowInfoMapper;

	@Autowired
	BookMapper bookMapper;

	@Autowired
	UserInfoMapper userInfoMapper;

	@Autowired
	UserMapper userMapper;

	/**
	 * @apiNote 查询用户书架
	 * @param userId
	 * @return
	 */
	public List<BorrowInfo> getBookShelf(Integer userId) {
		List<BorrowInfo> data = borrowInfoMapper.getByUserIdAndBorrowStates(userId, 1);
		relatedQuery(data);
		return data;
	}

	/**
	 * @apiNote 分页获得用户所有的借阅记录
	 * @param userId
	 * @return
	 */
	public List<BorrowInfo> getUserBorrowHistory(Integer userId, Integer currentPage, Integer pageSize) {
		List<BorrowInfo> data = borrowInfoMapper.getByUserIdPagination(userId, (currentPage - 1) * pageSize, pageSize);
		relatedQuery(data);
		return data;
	}

	/**
	 * @apiNote 分页查询审核中的借阅记录
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<BorrowInfo> getReviewingBorrowInfos(Integer currentPage, Integer pageSize) {
		List<BorrowInfo> data = borrowInfoMapper.getByBorrowStatePagination(0, (currentPage - 1) * pageSize, pageSize);
		relatedQuery(data);
		return data;
	}

	/**
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<BorrowInfo> getBorrowingReviewingBorrowInfos(Integer currentPage, Integer pageSize) {
		List<BorrowInfo> data = borrowInfoMapper.getByBorrowStatePagination(1, (currentPage - 1) * pageSize, pageSize);
		relatedQuery(data);
		return data;
	}

	/**
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<BorrowInfo> getFinishedBorrowInfos(Integer currentPage, Integer pageSize) {
		List<BorrowInfo> data = borrowInfoMapper.getByBorrowStatePagination(2, (currentPage - 1) * pageSize, pageSize);
		relatedQuery(data);
		return data;
	}

	public void approve(Integer id) {
		borrowInfoMapper.approve(id);
	}

	public void returnBook(Integer id) {
		borrowInfoMapper.updateStates(id, 2);
	}

	public void renewBook(Integer id) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 30);
		BorrowInfo borrowInfo = borrowInfoMapper.getById(id);
		borrowInfo.setBorrowDate(new Timestamp(System.currentTimeMillis()));
		borrowInfo.setReturnDate(new Timestamp(cal.getTime().getTime()));
		borrowInfo.setRenewState(1);
		borrowInfoMapper.update(borrowInfo);
	}

//	public int getCountByUserId(Integer id) {
//
//	}

	private void relatedQuery(List<BorrowInfo> data) {
		for (BorrowInfo info : data) {
			info.setBook(bookMapper.queryById(info.getBookId()));
			info.setUserInfo(userInfoMapper.findUserInfoByUserId(info.getUserId()));
		}
	}
}
