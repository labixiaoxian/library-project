package com.wyu.service.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyu.dao.BookMapper;
import com.wyu.dao.CountryMapper;
import com.wyu.dao.ThemeMapper;
import com.wyu.dao.TypeMapper;
import com.wyu.dto.BookDto;
import com.wyu.entity.Book;
import com.wyu.entity.Country;
import com.wyu.entity.Theme;
import com.wyu.entity.Type;
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
	
	@Autowired
	private CountryMapper countryMapper;
	
	@Autowired
	private ThemeMapper themeMapper;
	
	@Autowired
	private TypeMapper typeMapper;
	
	/**
	 * @apiNote 添加书籍信息
	 * @param book
	 */
	@Override
	public void addBook(Book book) {
		bookMapper.newBook(book);
	}

	/**
	 * @apiNote 删除书籍信息
	 * @param id
	 */
	@Override
	public void deleteById(int id) {
		bookMapper.deleteById(id);
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
	public void updateBook(Book book) {
		bookMapper.updateBook(book);
	}
	/**
	 * @apiNote 通过Id查询书籍信息
	 * @param 
	 */
	@Override
	public Book queryById(int id) {
		Book book = bookMapper.queryById(id);
		return book;
	}

	@Override
	public void insertBookImport(List<BookDto> list) {
		for (BookDto bookDto : list) {
			if(bookDto.getBookName()==null) {
				break;
			}
			try {
				countryMapper.newCountry(new Country(bookDto.getCountry()));
			} catch (Exception e) {
				
			}
			try {
				themeMapper.newTheme(new Theme(bookDto.getTheme()));
			} catch (Exception e) {
				
			}
			try {
				typeMapper.newType(new Type(bookDto.getType()));
			}catch (Exception e) {
				
			}
			Country country = countryMapper.queryByName(bookDto.getCountry());
			Theme theme = themeMapper.queryByName(bookDto.getTheme());
			Type type = typeMapper.queryByName(bookDto.getType());
			Book book = new Book(bookDto.getBookName(), country, theme, type, bookDto.getSpace(), 
								Integer.parseInt(bookDto.getBookCount()), bookDto.getInfo(), 
								new Timestamp(new Date().getTime()));
			bookMapper.newBook(book);
		}
	}

}
