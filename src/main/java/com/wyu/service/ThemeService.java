package com.wyu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyu.dao.ThemeMapper;
import com.wyu.entity.Theme;

@Service
public class ThemeService {
	@Autowired
	private ThemeMapper themeMapper;
	/*
	 * 查询所有主题
	 */
	public List<Theme> queryAll(){
		
		List<Theme> themeList = themeMapper.queryAll();
		return themeList;
	}
	/*
	 * 按ID查询所有主题
	 */
	public Theme queryById(int id) {
		Theme theme = themeMapper.queryById(id);
		return theme;
	}
	/*
	 * 添加一个主题
	 */
	public int insertTheme(Theme theme) {
		int flag = themeMapper.newTheme(theme);
		return flag;
	}
	/*
	 * 更新一个主题名
	 */
	public int updateById(Theme theme,String themeName) {
		int flag = themeMapper.updateById(theme, themeName);
		return flag;
	}
	/*
	 *  删除一个主题
	 */
	public int deleteById() {
		int flag = themeMapper.deleteById();
		return flag;
	}
}
