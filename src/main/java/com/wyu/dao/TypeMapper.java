package com.wyu.dao;

import java.util.List;

import com.wyu.entity.Type;

public interface TypeMapper {
	public List<Type> queryAll();
	
	public Type queryById(int id);
	
	public int newType(Type type);
	
	public int deleteById();
	
	public int updateById(Type type,String typeName);
}
