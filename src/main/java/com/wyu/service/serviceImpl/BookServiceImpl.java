package com.wyu.service.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@CacheConfig(cacheNames = "book")
@Transactional
public class BookServiceImpl implements BookService {
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
	@CacheEvict(allEntries = true)
	public void addBook(Book book) {
		bookMapper.newBook(book);
	}

	/**
	 * @apiNote 删除书籍信息
	 * @param id
	 */
	@Override
	@CacheEvict(allEntries = true)
	public void deleteById(int id) {
		bookMapper.deleteById(id);
	}

	/**
	 * 
	 * @apiNote 导入书籍信息
	 */
	@Override
	@CacheEvict(allEntries = true)
	public void insertBookImport(List<BookDto> list) {
		for (BookDto bookDto : list) {
			if (bookDto.getBookName() == null) {
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
			} catch (Exception e) {

			}
			Country country = countryMapper.queryByName(bookDto.getCountry());
			Theme theme = themeMapper.queryByName(bookDto.getTheme());
			Type type = typeMapper.queryByName(bookDto.getType());
			Book book = new Book(bookDto.getBookName(), country, theme, type, bookDto.getSpace(),
					Integer.parseInt(bookDto.getBookCount()), bookDto.getInfo(), new Timestamp(new Date().getTime()));
			bookMapper.newBook(book);
		}
	}

	/**
	 * @apiNote 查询所有书籍信息
	 */
	@Override
	@Cacheable(keyGenerator = "myGenerator", sync = true)
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
	@Cacheable(keyGenerator = "myGenerator", sync = true)
	public List<Book> queryAllDivPage(int current, int size) {
		List<Book> list = bookMapper.queryAllDivPage((current - 1) * size, size);
		return list;
	}

	/**
	 * @apiNote 通过Id查询书籍信息
	 * @param
	 */
	@Override
	@Cacheable(keyGenerator = "myGenerator", sync = true)
	public Book queryById(int id) {
		Book book = bookMapper.queryById(id);
		return book;
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
	@Cacheable(keyGenerator = "myGenerator", sync = true)
	public List<Book> queryLikeName(String name, int country_id, int theme_id, int type_id, String space) {
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
<<<<<<< HEAD
	@Cacheable(keyGenerator = "myGenerator", sync = true)
	public List<Book> queryLikeNameDivPage(String name, int country_id, int theme_id, int type_id, String space,
			int current, int size) {
=======
	@Cacheable(keyGenerator = "myGenerator")
	public List<Book> queryLikeNameDivPage(String name, Integer country_id, Integer theme_id, Integer type_id, String space,
										   Integer current, Integer size) {
>>>>>>> 5eddedf6e9b26a642c012a708210d91df477efec
		List<Book> list = bookMapper.queryByNameLikeDivPage(name, country_id, theme_id, type_id, space,
				(current - 1) * size, size);
		return list;
	}

	/**
	 * @apiNote 更新书籍信息
	 * @param book
	 */
	@Override
	@CacheEvict(allEntries = true)
	public void updateBook(Book book) {
		bookMapper.updateBook(book);
	}

<<<<<<< HEAD
=======
	/**
	 * @apiNote 通过Id查询书籍信息
	 * @param
	 */
	@Override
	@Cacheable(keyGenerator = "myGenerator")
	public Book queryById(int id) {
		Book book = bookMapper.queryById(id);
		return book;
	}

	/**
	 * 
	 * @apiNote 导入书籍信息
	 */
	@Override
	@CacheEvict(allEntries = true)
	public void insertBookImport(List<BookDto> list) {
		for (BookDto bookDto : list) {
			if (bookDto.getBookName() == null) {
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
			} catch (Exception e) {

			}
			Country country = countryMapper.queryByName(bookDto.getCountry());
			Theme theme = themeMapper.queryByName(bookDto.getTheme());
			Type type = typeMapper.queryByName(bookDto.getType());
			Book book = new Book(bookDto.getBookName(), country, theme, type, bookDto.getSpace(),
					Integer.parseInt(bookDto.getBookCount()), bookDto.getInfo(), new Timestamp(new Date().getTime()));
			bookMapper.newBook(book);
		}
	}




>>>>>>> 5eddedf6e9b26a642c012a708210d91df477efec
	@Override
	public int queryDivPageCount(String name, Integer country_id, Integer theme_id, Integer type_id, String space) {
		return bookMapper.queryDivPageCount(name, country_id, theme_id, type_id, space);
	}

}
