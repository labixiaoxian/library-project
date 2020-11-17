package com.wyu.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 
 * @author 李达成
 * @since 2020/11/17
 *
 */
public class BorrowInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 借阅ID
	private Integer id;
	// 用户ID
	private Integer userId;
	// 图书ID
	private Integer bookId;
	// 借阅日期
	private Timestamp borrowDate;
	// 应归还日期，一般为借阅日期+30天
	private Timestamp returnDate;
	// 审核状态，0为未审核，1为审核通过
	private Integer examineState;
	// 借阅状态，0为审核中，1为借阅中，2为借阅完毕，已归还
	private Integer borrowState;
	// 续借状态，0为未续借，1为已续借过
	private Integer renewState;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Timestamp getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Timestamp borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Timestamp getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Timestamp returnDate) {
		this.returnDate = returnDate;
	}

	public Integer getExamineState() {
		return examineState;
	}

	public void setExamineState(Integer examineState) {
		this.examineState = examineState;
	}

	public Integer getBorrowState() {
		return borrowState;
	}

	public void setBorrowState(Integer borrowState) {
		this.borrowState = borrowState;
	}

	public Integer getRenewState() {
		return renewState;
	}

	public void setRenewState(Integer renewState) {
		this.renewState = renewState;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookId, borrowDate, borrowState, examineState, id, renewState, returnDate, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BorrowInfo)) {
			return false;
		}
		BorrowInfo other = (BorrowInfo) obj;
		return Objects.equals(bookId, other.bookId) && Objects.equals(borrowDate, other.borrowDate)
				&& Objects.equals(borrowState, other.borrowState) && Objects.equals(examineState, other.examineState)
				&& Objects.equals(id, other.id) && Objects.equals(renewState, other.renewState)
				&& Objects.equals(returnDate, other.returnDate) && Objects.equals(userId, other.userId);
	}

	public BorrowInfo(Integer id, Integer userId, Integer bookId, Timestamp borrowDate, Timestamp returnDate,
			Integer examineState, Integer borrowState, Integer renewState) {
		// super();
		this.id = id;
		this.userId = userId;
		this.bookId = bookId;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
		this.examineState = examineState;
		this.borrowState = borrowState;
		this.renewState = renewState;
	}

	public BorrowInfo() {
		// super();
	}

}
