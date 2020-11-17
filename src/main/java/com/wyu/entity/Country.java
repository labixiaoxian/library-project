package com.wyu.entity;
/*
 * 国家实体类
 */
public class Country {
	private int id;              //国家Id
	private String countryName;  //国家名
	
	public Country() {
		super();
		// TODO Auto-generated constructor stub
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
	
}
