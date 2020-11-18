package com.wyu.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/*
* 图书实体类
 */
public class Book implements Serializable{
	private int id;             //图书ID
	private String bookName;    //图书名
	private Country country;    //国家
	private Theme theme;        //主题
	private Type type;          //类型
	private String space;       //篇幅
	private int bookCount;      //图书数量
	private String info;        //详细信息
	private Timestamp createDate;  //图书上架时间
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
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
	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", country=" + country + ", theme=" + theme + ", type="
				+ type + ", space=" + space + ", bookCount=" + bookCount + ", info=" + info + ", createDate="
				+ createDate + "]";
	}
}
