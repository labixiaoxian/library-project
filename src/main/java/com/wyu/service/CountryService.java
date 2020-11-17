package com.wyu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyu.dao.CountryMapper;
import com.wyu.entity.Country;

@Service
public class CountryService {
	@Autowired
	private CountryMapper countryMapper;
	/*
	 * 查询全部国家
	 */
	public List<Country> queryAll(){
		List<Country> countryList = countryMapper.queryAll();
		return countryList;
	}
	public Country queryById(int id) {
		Country country = countryMapper.queryById(id);
		return country;
	}
	public int insertCountry(Country country) {
		int flag = countryMapper.newCountry(country);
		return flag;
	}
	public int updateById(Country country,String countryName) {
		int flag = countryMapper.updateById(country, countryName);
		return flag;
	}
	public int deleteById() {
		int id = 1;
		int flag = countryMapper.deleteById(id);
		return flag;
	}
}
