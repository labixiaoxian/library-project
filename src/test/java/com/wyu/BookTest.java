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
	private BookMapper bookMapper;
	@Autowired
	private CountryMapper countryMapper;
	@Autowired
	private ThemeMapper themeMapper;
	@Autowired
	private TypeMapper typeMapper;
	@Test
	void insertBook() {
		for(int i=1;i<100;i++) {
			Book book = new Book();
			Country country = countryMapper.queryById(1);
			Theme theme = themeMapper.queryById(1);
			Type type = typeMapper.queryById(1);
			book.setBookName("三只小猪"+i);
			book.setCountry(country);
			book.setTheme(theme);
			book.setType(type);
			book.setInfo("三只小猪建造房子的故事"+i);
			book.setBookCount(100);
			book.setSpace("短篇");
			int flag = bookMapper.newBook(book);
			System.out.println(flag);
		}
	}
	
	@Test
	void queryAll() {
		List<Book> list= bookMapper.queryAll();
		System.out.println(list.get(5).getCountry().getId());
		//list.forEach(li->System.out.println(li));
	}
	
	@Test
	void queryById() {
		int id = 10;
		Book book = bookMapper.queryById(id);
		System.out.println(book);
	}
	
	@Test
	void queryLikeName() {
		String name = "猪";
		String space = "短篇小说";
		int country_id = 0;
		int theme_id = 1;
		int type_id = 0;
		List<Book> list= bookMapper.queryByNameLike(name,country_id,theme_id,type_id,space);
		list.forEach(li->System.out.println(li));
	}
	
	@Test
	void deleteBook() {
		int id = 99;
		int row = bookMapper.deleteById(id);
		System.out.println(row);
	}
	
	@Test
	void updateBook() {
		Book book = new Book();
		book.setId(2);
		book.setBookName("三只大猪");
		book.setBookCount(200);
		int row = bookMapper.updateBook(book);
		System.out.println(row);
	}
	
	@Test
	void queryAllDivPage() {
		int current = 3;
		int size = 8;
		int start = (current-1)*size;
		List<Book> list = bookMapper.queryAllDivPage(start, size);
		list.forEach(li->System.out.println(li));
	}
	
	@Test
	void queryByNameLikeDivPage() {
		int current = 1;
		int size = 5;
		String name = "猪";
		String space = "1000";
		int country_id = 1;
		int theme_id = 1;
		int type_id = 1;
		int start = (current-1)*size;
		List<Book> list = bookMapper.queryByNameLikeDivPage(name,country_id,theme_id,type_id,space, start, size);
		list.forEach(li->System.out.println(li));
	}
}
