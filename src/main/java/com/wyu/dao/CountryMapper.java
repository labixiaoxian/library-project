package com.wyu.dao;

import java.util.List;

import com.wyu.entity.Country;
/**
 * 
 * @author 李润东
 * @since 2020/11/17
 *
 */
public interface CountryMapper {
	/**
	 * @apiNote 查询所有的国家
	 * @return
	 */
	public List<Country> queryAll();
	
	/**
	 * @apiNote 通过id查询国家信息
	 * @param id
	 * @return
	 */
	public Country queryById(int id);
	/**
	 * @apiNote 新增一个国家信息
	 * @param country
	 * @return
	 */
	public void newCountry(Country country);
	/**
	 * @apiNote 删除一个国家信息
	 * @param id
	 * @return
	 */
	public void deleteById(int id);
	/**
	 * @apiNote 更新一个国家信息
	 * @param country
	 * @param countryName
	 * @return
	 */
	public void update(Country country);
}
