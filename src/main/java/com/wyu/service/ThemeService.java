package com.wyu.service;

import java.util.List;

import com.wyu.entity.Theme;
/**
 * 
 * @since 2020/11/18
 * @author 李润东
 *
 */
public interface ThemeService {
	
	public List<Theme> queryAll();
	public Theme queryById(int id);
	public void insertTheme(Theme theme);
	public void updateById(Theme theme);
	public void deleteById(int id);
}
