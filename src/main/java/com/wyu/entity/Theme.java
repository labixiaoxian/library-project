package com.wyu.entity;

import java.io.Serializable;

/*
 * 主题实体类
 */
public class Theme implements Serializable{
	private int id;           //主题ID
	private String themeName;   //主题名
	public Theme() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Theme(String themeName) {
		super();
		this.themeName = themeName;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getThemeName() {
		return themeName;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	@Override
	public String toString() {
		return "Theme [id=" + id + ", themeName=" + themeName + "]";
	}
}
