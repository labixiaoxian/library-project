package com.wyu.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
*
* @author 李润东
* @since 2020/11/17
*
*/
@ExcelTarget("books")
public class Book implements Serializable{
	@Excel(name = "图书编号",orderNum = "0")
	private int id;             //图书ID
	@Excel(name = "图书名",orderNum = "1",width = 35.0)
	private String bookName;    //图书名
	@Excel(name = "作者",orderNum = "2")
	private String author;      //作者
	@ExcelEntity
	private Country country;    //国家
	@ExcelEntity
	private Theme theme;        //主题
	@ExcelEntity
	private Type type;          //类型
	@Excel(name = "篇幅",orderNum = "6")
	private String space;       //篇幅
	@Excel(name = "图书数量",orderNum = "7")
	private int bookCount;      //图书数量
	@Excel(name = "详细信息",width = 60.0,orderNum = "8")
	private String info;        //详细信息
	@Excel(name = "图书上架时间",width = 35.0,format = "yyyy-MM-dd HH:mm:ss",orderNum = "9")
	private Timestamp createDate;  //图书上架时间
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Book(String bookName, String author, Country country, Theme theme, Type type, String space, int bookCount,
			String info, Timestamp createDate) {
		super();
		this.bookName = bookName;
		this.author = author;
		this.country = country;
		this.theme = theme;
		this.type = type;
		this.space = space;
		this.bookCount = bookCount;
		this.info = info;
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public int getBookCount() {
		return bookCount;
	}

	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	
}
