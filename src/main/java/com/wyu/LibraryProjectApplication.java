package com.wyu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wyu.dao")
public class LibraryProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryProjectApplication.class, args);
	}

}
