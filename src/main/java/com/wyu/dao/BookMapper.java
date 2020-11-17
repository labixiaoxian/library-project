package com.wyu.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.wyu.entity.Book;

public interface BookMapper {
	
	public List<Book> queryAll();
	
	public List<Book> queryByNameLike(@Param(value = "name") String name);
	
	public int newBook(Book book);
	
	public int deleteById();
	
}
