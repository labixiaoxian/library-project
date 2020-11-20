package com.wyu.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

@ExcelTarget("bookimp")
public class BookDto {
	@Excel(name = "图书名")
	private String bookName;
	@Excel(name = "国家")
	private String country;
	@Excel(name = "主题")
	private String theme;
	@Excel(name = "类型")
	private String type;
	@Excel(name = "篇幅")
	private String space;
	@Excel(name = "图书数量")
	private String bookCount;
	@Excel(name = "详细信息")
	private String info;
	public BookDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BookDto(String bookName, String country, String theme, String type, String space, String bookCount,
			String info) {
		super();
		this.bookName = bookName;
		this.country = country;
		this.theme = theme;
		this.type = type;
		this.space = space;
		this.bookCount = bookCount;
		this.info = info;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public String getBookCount() {
		return bookCount;
	}

	public void setBookCount(String bookCount) {
		this.bookCount = bookCount;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "BookDto [bookName=" + bookName + ", country=" + country + ", theme=" + theme + ", type=" + type
				+ ", bookCount=" + bookCount + ", info=" + info + "]";
	}
}
