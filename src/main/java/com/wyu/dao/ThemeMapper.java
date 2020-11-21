package com.wyu.dao;

import java.util.List;

import com.wyu.entity.Theme;
/**
 * 
 * @author 李润东
 * @since 2020/11/17
 *
 */
public interface ThemeMapper {
	/**
	 * @apiNote 查询所有主题信息
	 * @return
	 */
	public List<Theme> queryAll();
	/**
	 * @apiNote 通过id查询主题信息
	 * @param id
	 * @return
	 */
	public Theme queryById(int id);
	/**
	 * @apiNote 通过主题名查询主题信息
	 * @param name
	 * @return
	 */
	public Theme queryByName(String name);
	/**
	 * @apiNote 新增一个主题信息
	 * @param theme
	 * @return
	 */
	public void newTheme(Theme theme);
	/**
	 * @apiNote 通过id删除一个主题信息
	 * @param id
	 * @return
	 */
	public void deleteById(int id);
	/**
	 * @apiNote 更新一个主题信息
	 * @param theme
	 * @param themeName
	 * @return
	 */
	public void update(Theme theme);
}
