package com.wyu.dao;

import java.util.List;

import com.wyu.entity.Country;

public interface CountryMapper {
	public List<Country> queryAll();
	
	public Country queryById(int id);
	
	public int newCountry(Country country);
	
	public int deleteById();
	
	public int updateById(Country country,String countryName);
}
