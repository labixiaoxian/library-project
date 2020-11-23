package com.wyu.entity;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/*
 * 类型实体类
 */
@ExcelTarget("type")
public class Type implements Serializable{
	@ExcelIgnore
	@Excel(name = "类型ID")
	private int id;             //类型ID
	@Excel(name = "类型",orderNum = "5")
	private String typeName;    //类型名
	public Type() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Type(String typeName) {
		super();
		this.typeName = typeName;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		return "Type [id=" + id + ", typeName=" + typeName + "]";
	}
}
