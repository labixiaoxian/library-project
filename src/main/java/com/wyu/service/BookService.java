package com.wyu.service;

import java.util.List;

import com.wyu.dto.BookDto;
import com.wyu.entity.Book;
import org.springframework.data.repository.query.Param;

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
	List<Book> queryLikeNameDivPage(String name,Integer country_id,Integer theme_id,Integer type_id,String space,Integer current,Integer size);
	void updateBook(Book book);
	void insertBookImport(List<BookDto> list);

	/**
	 * 统计数量
	 * @param name
	 * @param country_id
	 * @param theme_id
	 * @param type_id
	 * @param space
	 * @return
	 */
	public int queryDivPageCount(String name,Integer country_id,Integer theme_id,Integer type_id,String space);
}
