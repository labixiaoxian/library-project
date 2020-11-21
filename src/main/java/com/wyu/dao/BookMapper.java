package com.wyu.dao;

import java.util.List;



import com.wyu.entity.Book;
import org.apache.ibatis.annotations.Param;

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
	 * @apiNote 查询书籍数量
	 * @return
	 */
	public int queryAllCount();
	/**
	 * @apiNote 查询书籍数量（模糊查询）
	 * @return
	 */
	public int queryByLikeCount(@Param("name") String name,
			@Param("country_id") int country_id,@Param("theme_id") int theme_id,
			@Param("type_id") int type_id,@Param("space") String space);
	/**
	 * @apiNote 通过ID查询书籍
	 * @param id
	 * @return
	 */
	public Book queryById(int id);
	/**
	 * @apiNote 书名的模糊查询
	 * @param name
	 * @param country_id
	 * @param theme_id
	 * @param type_id
	 * @param space
	 * @return
	 */
	public List<Book> queryByNameLike(@Param("name") String name,
			@Param("country_id") int country_id,@Param("theme_id") int theme_id,
			@Param("type_id") int type_id,@Param("space") String space);
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
	 * @param country_id
	 * @param theme_id
	 * @param type_id
	 * @param space
	 * @param current
	 * @param size
	 * @return
	 */
	public List<Book> queryByNameLikeDivPage(@Param("name") String name,
			@Param("country_id")int country_id,@Param("theme_id")int theme_id,
			@Param("type_id")int type_id,@Param("space")String space,
			@Param("current")int current,@Param("size")int size);


	/**
	 * 统计数量
	 * @param name
	 * @param country_id
	 * @param theme_id
	 * @param type_id
	 * @param space
	 * @return
	 */
	public int queryDivPageCount(@Param("name") String name, @Param("country_id")Integer country_id, @Param("theme_id")Integer theme_id,
								 @Param("type_id")Integer type_id, @Param("space")String space);
}
