package com.wyu;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@MapperScan("com.wyu.dao")
@SpringBootTest
class LibraryProjectApplicationTests {
	
	@Test
	void contextLoads() {
	}
}
