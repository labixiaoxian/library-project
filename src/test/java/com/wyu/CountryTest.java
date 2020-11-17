package com.wyu;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wyu.entity.Country;
import com.wyu.service.CountryService;

@MapperScan("com.wyu.dao")
@SpringBootTest
public class CountryTest {
	@Autowired
	CountryService countryService;
	
	@Test
	void countryQueryAll() {
		List<Country> list=  countryService.queryAll();
		list.forEach(li->System.out.println(li));
	}
	
	@Test
	void countryQueryById() {
		int id = 1;
		Country country = countryService.queryById(id);
		System.out.println(country);
	}
}
