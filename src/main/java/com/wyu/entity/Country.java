package com.wyu.entity;

import java.io.Serializable;

/*
 * 国家实体类
 */
public class Country implements Serializable{
	private int id;              //国家Id
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
