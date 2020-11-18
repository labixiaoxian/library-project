package com.wyu.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * 
 * @author 李达成
 * @since 2020/11/17
 *
 */
public class Punishment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 惩罚ID
	private Integer id;
	// 用户
	private Integer userId;
	// 罚金
	private int fine;
	// 罚款原因
	private String reason;

	// 用户信息
	private UserInfo userInfo;

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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

	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "Punishment [id=" + id + ", userId=" + userId + ", fine=" + fine + ", reason=" + reason + ", userInfo="
				+ userInfo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(fine, id, reason, userId, userInfo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Punishment)) {
			return false;
		}
		Punishment other = (Punishment) obj;
		return fine == other.fine && Objects.equals(id, other.id) && Objects.equals(reason, other.reason)
				&& Objects.equals(userId, other.userId) && Objects.equals(userInfo, other.userInfo);
	}

	public Punishment(Integer id, Integer userId, int fine, String reason) {
		// super();
		this.id = id;
		this.userId = userId;
		this.fine = fine;
		this.reason = reason;
	}

	public Punishment() {
		// super();
	}

}
