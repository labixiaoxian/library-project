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
	/**
	 * @apiNote 查询所有书籍信息
	 */
	@Override
	public List<Book> queryAll() {
		List<Book> list = bookMapper.queryAll();
		return list;
	}
	/**
	 * @apiNote 查询所有书籍信息（分页）
	 * @param current
	 * @param size
	 */
	@Override
	public List<Book> queryAllDivPage(int current,int size) {
		List<Book> list = bookMapper.queryAllDivPage((current-1)*size, size);
		return list;
	}
	/**
	 * @apiNote 查询所有书籍信息（模糊查询）
	 * @param name
	 * @param country_id
	 * @param theme_id
	 * @param type_id
	 * @param space
	 */
	@Override
	public List<Book> queryLikeName(String name,int country_id,int theme_id,int type_id,String space) {
		List<Book> list = bookMapper.queryByNameLike(name, country_id, theme_id, type_id, space);
		return list;
	}
	/**
	 * @apiNote 查询所有书籍信息（模糊查询）（分页）
	 * @param name
	 * @param country_id
	 * @param theme_id
	 * @param type_id
	 * @param space
	 * @param current
	 * @param size
	 */
	@Override
	public List<Book> queryLikeNameDivPage(String name,int country_id,int theme_id,int type_id,String space,int current,int size) {
		List<Book> list = bookMapper.queryByNameLikeDivPage(name,country_id,theme_id,type_id,space, (current-1)*size, size);
		return list;
	}
	/**
	 * @apiNote 更新书籍信息
	 * @param book
	 */
	@Override
	public int updateBook(Book book) {
		int row = 0;
		row = bookMapper.updateBook(book);
		return row;
	}

}