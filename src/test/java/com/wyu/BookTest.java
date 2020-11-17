package com.wyu;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wyu.dao.BookMapper;
import com.wyu.dao.CountryMapper;
import com.wyu.dao.ThemeMapper;
import com.wyu.dao.TypeMapper;
import com.wyu.entity.Book;
import com.wyu.entity.Country;
import com.wyu.entity.Theme;
import com.wyu.entity.Type;

@MapperScan("com.wyu.dao")
@SpringBootTest
public class BookTest {
	@Autowired
	BookMapper bookMapper;
	@Autowired
	CountryMapper countryMapper;
	@Autowired
	ThemeMapper themeMapper;
	@Autowired
	TypeMapper typeMapper;
	@Test
	void insertBook() {
		Book book = new Book();
		Country country = countryMapper.queryById(1);
		Theme theme = themeMapper.queryById(1);
		Type type = typeMapper.queryById(1);
		book.setBookName("三只小猪");
		book.setCountry(country);
		book.setTheme(theme);
		book.setType(type);
		book.setInfo("三只小猪建造房子的故事");
		book.setBookCount(100);
		book.setSpace("1000");
		int flag = bookMapper.newBook(book);
		System.out.println(flag);
	}
	
	@Test
	void queryAll() {
		List<Book> list= bookMapper.queryAll();
		list.forEach(li->System.out.println(li));
	}
	
	@Test
	void queryLikeName() {
		String name = "猪";
		List<Book> list= bookMapper.queryByNameLike(name);
		list.forEach(li->System.out.println(li));
	}
}
