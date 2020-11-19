package com.wyu.dao;

import java.util.List;

import com.wyu.entity.Type;
/**
 * 
 * @author 李润东
 * @since 2020/11/17
 *
 */
public interface TypeMapper {
	/**
	 * @apiNote 查询所有类型信息
	 * @return
	 */
	public List<Type> queryAll();
	/**
	 * @apiNote 通过id查询类型信息
	 * @param id
	 * @return
	 */
	public Type queryById(int id);
	/**
	 * 新增类型信息
	 * @param type
	 * @return
	 */
	public void newType(Type type);
	/**
	 * @apiNote 通过id删除一个类型信息
	 * @param id
	 * @return
	 */
	public void deleteById(int id);
	/**
	 * @apiNote 更新一条类型信息
	 * @param type
	 * @param typeName
	 * @return
	 */
	public void updateById(Type type);
}
