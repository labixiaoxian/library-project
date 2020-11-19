package com.wyu.service;

import java.util.List;

import com.wyu.entity.Type;
/**
*
* @author 李润东
* @since 2020/11/19
*
*/
public interface TypeService {
	
     List<Type> queryAll();
	 Type queryById(int id);
	 void insertType(Type type);
	 void updateById(Type type);
	 void deleteById(int id);
}
