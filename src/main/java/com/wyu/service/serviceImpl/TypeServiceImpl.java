package com.wyu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyu.dao.TypeMapper;
import com.wyu.entity.Type;
import com.wyu.service.TypeService;
/**
*
* @author 李润东
* @since 2020/11/19
*
*/
@Service
public class TypeServiceImpl implements TypeService{

	@Autowired
	private TypeMapper typeMapper;
	/**
	 * 
	 * @apiNote 查询全部类型信息
	 * 
	 */
	@Override
	public List<Type> queryAll() {
		List<Type> list = typeMapper.queryAll();
		return list;
	}
	/**
	 * 
	 * @apiNote 通过id查询类型
	 * @param id
	 * 
	 */
	@Override
	public Type queryById(int id) {
		Type type = typeMapper.queryById(id);
		return type;
	}
	/**
	 * 
	 * @apiNote 新增一条类型信息
	 * @param type
	 * 
	 */
	@Override
	public void insertType(Type type) {
		typeMapper.newType(type);
	}

	/**
	 * 
	 * @apiNote 更新一条类型信息
	 * @param type
	 * 
	 */
	@Override
	public void updateById(Type type) {
		typeMapper.updateById(type);
	}
	/**
	 * 
	 * @apiNote 通过id删除一条类型信息
	 * @param id
	 * 
	 */
	@Override
	public void deleteById(int id) {
		typeMapper.deleteById(id);
	}

}
