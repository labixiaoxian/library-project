package com.wyu.entity;
/*
 * 类型实体类
 */
public class Type {
	private int id;             //类型ID
	private String typeName;    //类型名
	public Type() {
		super();
		// TODO Auto-generated constructor stub
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
	
}
