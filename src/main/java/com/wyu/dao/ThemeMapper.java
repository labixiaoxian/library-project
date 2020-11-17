package com.wyu.dao;

import java.util.List;

import com.wyu.entity.Theme;

public interface ThemeMapper {
	
	public List<Theme> queryAll();
	
	public Theme queryById(int id);
	
	public int newTheme(Theme theme);
	
	public int deleteById();
	
	public int updateById(Theme theme,String themeName);
}
