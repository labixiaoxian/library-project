package com.wyu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyu.dao.TypeMapper;
import com.wyu.entity.Type;

@Service
public class TypeService {
	@Autowired
	private TypeMapper typeMapper;
	
	public List<Type> queryAll(){
		
		List<Type> typeList = typeMapper.queryAll();
		return typeList;
	}
	public Type queryById(int id) {
		Type type = typeMapper.queryById(id);
		return type;
	}
	public int insertType(Type type) {
		int flag = typeMapper.newType(type);
		return flag;
	}
	public int updateById(Type type,String typeName) {
		int flag = typeMapper.updateById(type, typeName);
		return flag;
	}
	public int deleteById() {
		int flag = typeMapper.deleteById();
		return flag;
	}
}
