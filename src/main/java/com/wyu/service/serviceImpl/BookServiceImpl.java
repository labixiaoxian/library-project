package com.wyu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyu.dao.BookMapper;
import com.wyu.entity.Book;
import com.wyu.service.BookService;
/**
 * @since 2020/11/18
 * @author 李润东
 *
 */
@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookMapper bookMapper;
	
	/**
	 * @apiNote 添加书籍信息
	 * @param book
	 */
	@Override
	public int addBook(Book book) {
		int row = 0;
		row = bookMapper.newBook(book);
		return row;
	}

	/**
	 * @apiNote 删除书籍信息
	 * @param id
	 */
	@Override
	public int deleteById(int id) {
		int row = 0;
		row = bookMapper.deleteById(id);
		return row;
	}

	@Override
	public List<Book> queryAll() {
		List<Book> list = bookMapper.queryAll();
		return list;
	}

	@Override
	public List<Book> queryAllDivPage(int current,int size) {
		List<Book> list = bookMapper.queryAllDivPage((current-1)*size, size);
		return list;
	}

	@Override
	public List<Book> queryLikeName(String name) {
		List<Book> list = bookMapper.queryByNameLike(name);
		return list;
	}

	@Override
	public List<Book> queryLikeNameDivPage(String name,int current,int size) {
		List<Book> list = bookMapper.queryByNameLikeDivPage(name, (current-1)*size, size);
		return list;
	}

	@Override
	public int updateBook(Book book) {
		int row = 0;
		row = bookMapper.updateBook(book);
		return row;
	}

}
