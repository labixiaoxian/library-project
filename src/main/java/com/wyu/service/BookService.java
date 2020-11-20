package com.wyu.service;

import java.util.List;

import com.wyu.dto.BookDto;
import com.wyu.entity.Book;
/**
 * @since 2020/11/18
 * @author 李润东
 *
 */
public interface BookService {
	
	void addBook(Book book);
	void deleteById(int id);
	Book queryById(int id);
	List<Book> queryAll();
	List<Book> queryAllDivPage(int current,int size);
	List<Book> queryLikeName(String name,int country_id,int theme_id,int type_id,String space);
	List<Book> queryLikeNameDivPage(String name,int country_id,int theme_id,int type_id,String space,int current,int size);
	void updateBook(Book book);
	void insertBookImport(List<BookDto> list);
}
