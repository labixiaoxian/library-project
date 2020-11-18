package com.wyu.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.wyu.entity.Book;
/**
 * 
 * @author 李润东
 * @since 2020/11/17
 *
 */
public interface BookMapper {
	/**
	 * @apiNote 查询所有的书籍信息
	 * @return
	 */
	public List<Book> queryAll();
	/**
	 * @apiNote 通过ID查询书籍
	 * @param id
	 * @return
	 */
	public Book queryById(int id);
	/**
	 * @apiNote 书名的模糊查询
	 * @param name
	 * @return
	 */
	public List<Book> queryByNameLike(@Param("name") String name);
	/**
	 * @apiNote 新建书籍信息
	 * @param book
	 * @return
	 */
	public int newBook(Book book);
	/**
	 * @apiNote 通过删除书籍信息
	 * @param id
	 * @return
	 */
	public int deleteById(@Param(value = "id") int id);
	/**
	 * @apiNote 更新书籍信息
	 * @param book
	 * @return
	 */
	public int updateBook(Book book);
	/**
	 * @apiNote 查询所有的书籍信息分页
	 * @param current
	 * @param size
	 * @return
	 */
	public List<Book> queryAllDivPage(@Param("current")int current,@Param("size")int size);
	/**
	 * @apiNote 模糊查询分页
	 * @param name
	 * @param current
	 * @param size
	 * @return
	 */
	public List<Book> queryByNameLikeDivPage(@Param("name") String name,@Param("current")int current,@Param("size")int size);
}
