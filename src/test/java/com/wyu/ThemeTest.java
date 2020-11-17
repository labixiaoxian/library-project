package com.wyu;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wyu.entity.Theme;
import com.wyu.service.ThemeService;

@MapperScan("com.wyu.dao")
@SpringBootTest
public class ThemeTest {
	@Autowired
	ThemeService themeService;
	
	@Test
	void countryQueryAll() {
		List<Theme> list=  themeService.queryAll();
		list.forEach(li->System.out.println(li));
	}
	
	@Test
	void countryQueryById() {
		int id = 1;
		Theme theme = themeService.queryById(id);
		System.out.println(theme);
	}
}
