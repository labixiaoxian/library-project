package com.wyu;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wyu.entity.Type;
import com.wyu.service.TypeService;

@MapperScan("com.wyu.dao")
@SpringBootTest
public class TypeTest {
	@Autowired
	TypeService typeService;
	
	@Test
	void countryQueryAll() {
		List<Type> list=  typeService.queryAll();
		list.forEach(li->System.out.println(li));
	}
	
	@Test
	void countryQueryById() {
		int id = 1;
		Type Type = typeService.queryById(id);
		System.out.println(Type);
	}
}
