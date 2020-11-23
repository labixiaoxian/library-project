package com.wyu.entity;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/*
 * 国家实体类
 */
@ExcelTarget("country")
public class Country implements Serializable{
	@ExcelIgnore
	@Excel(name = "国家ID")
	private int id;              //国家Id
	@Excel(name = "国家",orderNum = "3")
	private String countryName;  //国家名
	
	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Country(String countryName) {
		super();
		this.countryName = countryName;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	@Override
	public String toString() {
		return "Country [id=" + id + ", countryName=" + countryName + "]";
	}
}
