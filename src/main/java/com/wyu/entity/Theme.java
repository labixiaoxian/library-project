package com.wyu.entity;
/*
 * 主题实体类
 */
public class Theme {
	private int id;           //主题ID
	private String themeId;   //主题名
	
	public Theme() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getThemeId() {
		return themeId;
	}
	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}
	
}
