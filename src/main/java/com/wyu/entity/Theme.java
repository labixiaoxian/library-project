package com.wyu.entity;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/*
 * 主题实体类
 */
@ExcelTarget("theme")
public class Theme implements Serializable{
	@ExcelIgnore
	@Excel(name = "主题ID")
	private int id;           //主题ID
	@Excel(name = "主题",orderNum = "4")
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
