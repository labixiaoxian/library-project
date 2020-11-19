package com.wyu.service;

import java.util.List;

import com.wyu.entity.Country;
/**
 * 
 * @since 2020/11/19
 * @author 李润东
 *
 */
public interface CountryService {
	
	public List<Country> queryAll();
	public Country queryById(int id);
	public void insert(Country country);
	public void update(Country country);
	public void deleteById(int id);
}
