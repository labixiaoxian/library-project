package com.wyu.vo;

import java.io.Serializable;
import java.util.Objects;

public class WriteBack<T> implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String msg;
	private int count;
	private T data;

	public WriteBack() {
		super();
	}

	public WriteBack(int code, String msg, int count, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public WriteBack(int code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof WriteBack)) {
			return false;
		}
		WriteBack other = (WriteBack) obj;
		return code == other.code && count == other.count && Objects.equals(data, other.data)
				&& Objects.equals(msg, other.msg);
	}

	public int getCode() {
		return code;
	}

	public T getData() {
		return data;
	}

	public String getMsg() {
		return msg;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, count, data, msg);
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "WriteBack [code=" + code + ", msg=" + msg + ", count=" + count + ", data=" + data + "]";
	}

}
