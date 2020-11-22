package com.wyu.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wyu.dao.BookMapper;
import com.wyu.dao.CountryMapper;
import com.wyu.dao.ThemeMapper;
import com.wyu.dao.TypeMapper;
import com.wyu.entity.Book;
import com.wyu.service.serviceImpl.BookServiceImpl;
import com.wyu.utils.WriteBackUtil;
import com.wyu.vo.WriteBack;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @since 2020/11/18
 * @author 李润东
 *
 */
@Api(value = "图书模块", tags = { "用于图书管理的相关接口" })
@RestController
public class BookController {
	@Autowired
	private BookServiceImpl bookServiceImpl;
	@Autowired
	private CountryMapper countryMapper;
	@Autowired
	private ThemeMapper themeMapper;
	@Autowired
	private TypeMapper typeMapper;
	@Autowired
	private BookMapper bookMapper;
//	@ApiOperation(notes = "查询全部图书信息",value = "查询全部图书信息")
//	@GetMapping("/book/query")
//	public WriteBack<List<Book>> queryAll() {
//		List<Book> list = bookServiceImpl.queryAll();
//		WriteBack<List<Book>> wb = new WriteBack<>();
//		wb.setCode(4396);
//		wb.setData(list);
//		wb.setMsg("查询所有");
//		return wb;
//	}

	@ApiOperation(notes = "通过Id查询图书信息", value = "通过Id查询图书信息")
	@GetMapping("/book/{id}")
	public WriteBack<Object> queryById(@PathVariable("id") int id) {
		WriteBack<Object> wb = new WriteBack<>();
		try {
			Book book = bookServiceImpl.queryById(id);
			wb.setData(book);
			WriteBackUtil.setSuccess(wb);
			return wb;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(wb);
			return wb;
		}
	}

	@ApiOperation(notes = "查询全部图书信息（分页）", value = "查询全部图书信息（分页）")
	@GetMapping("/book")
	public WriteBack<Object> queryAllDivPage(
			@ApiParam(name = "currentPage", value = "当前页码", required = true) @RequestParam("currentPage") int currentPage,
			@ApiParam(name = "pageSize", value = "一页显示数据的数量", required = true) @RequestParam("pageSize") int pageSize) {
		List<Book> list = bookServiceImpl.queryAllDivPage(currentPage, pageSize);
		int count = bookMapper.queryAllCount();
		WriteBack<Object> wb = new WriteBack<>();
		wb.setCode(369);
		wb.setCount(count);
		wb.setData(list);
		wb.setMsg("查询所有分页");
		return wb;
	}

	@ApiOperation(notes = "查询全部图书信息（模糊查询）（分页）", value = "查询全部图书信息（模糊查询）（分页）")
	@PostMapping("/book/query")
	public WriteBack<Object> queryAllByLike(
			@ApiParam(name = "json", value = "(单引号改为双引号)" + "{" + "'nickname':'nickname'," + "'currentPage':页数,"
					+ "'pageSize':一页数据量," + "'country_id':国家ID," + "'theme_id':主题ID," + "'type_id':类型ID,"
					+ "'space':篇幅下标（1-3）," + "}", required = true) @RequestBody Map<String, Object> requestMap) {
		String nickname = (String) requestMap.get("nickname");
		int currentPage = (int) requestMap.get("currentPage");
		int pageSize = (int) requestMap.get("pageSize");
		int country_id = (int) requestMap.get("country_id");
		int theme_id = (int) requestMap.get("theme_id");
		int type_id = (int) requestMap.get("type_id");
		int space_count = (int) requestMap.get("space");
		String space = null;
		WriteBack<Object> wb = new WriteBack<>();
		try {
			if (space_count == 1) {
				space = "短篇";
			} else if (space_count == 2) {
				space = "中篇";
			} else if (space_count == 3) {
				space = "长篇";
	
	@ApiOperation(notes = "查询全部图书信息（模糊查询）（分页）",value = "查询全部图书信息（模糊查询）（分页）")
	@PostMapping("/book/query")
	public WriteBack<Object> queryAllByLike(@ApiParam(name = "json", 
											value ="(单引号改为双引号)"+ "{" + "'nickname':'nickname',"
											+ "'currentPage':页数,"+"'pageSize':一页数据量,"+"'country_id':国家ID,"+
											"'theme_id':主题ID,"+"'type_id':类型ID,"+"'space':篇幅下标（1-3）,"+
													"}", required = true)@RequestBody Map<String,Object> requestMap){
		String nickname = (String) requestMap.get("nickname");
		Integer currentPage = (Integer) requestMap.get("currentPage");
		Integer pageSize = (Integer) requestMap.get("pageSize");
		Integer country_id = (Integer) requestMap.get("country_id");
		Integer theme_id = (Integer) requestMap.get("theme_id");
		Integer type_id = (Integer) requestMap.get("type_id");
		Integer space_count = (Integer) requestMap.get("space");
		String space=null;
		WriteBack<Object> wb = new WriteBack<Object>();
		try {
			if (!ObjectUtils.isEmpty(space_count)){
				if(space_count==1) {
					space="短篇";
				}else if(space_count==2) {
					space="中篇";
				}else if(space_count==3){
					space="长篇";
				}
			}
			List<Book> list = bookServiceImpl.queryLikeNameDivPage(nickname, country_id, theme_id, type_id, space,
					currentPage, pageSize);
			int count = bookMapper.queryByLikeCount(nickname, country_id, theme_id, type_id, space);
			wb.setData(list);
			wb.setCount(count);
			wb.setCode(999);
			wb.setMsg("success");
			return wb;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(wb);
			return wb;
		}
	}

	@ApiOperation(notes = "添加一本书籍信息", value = "添加一本书籍信息")
	@PostMapping("/book")
	public WriteBack<String> insert(@RequestBody Map<String, Object> requestMap) {
		String bookName = (String) requestMap.get("bookName");
		int country_id = (int) requestMap.get("country_id");
		int theme_id = (int) requestMap.get("theme_id");
		int type_id = (int) requestMap.get("type_id");
		int space_count = (int) requestMap.get("space_count");
		int bookCount = (int) requestMap.get("bookCount");
		String info = (String) requestMap.get("info");
		String space = null;
		WriteBack<String> wb = new WriteBack<>();
		try {
			if (space_count == 1) {
				space = "短篇";
			} else if (space_count == 2) {
				space = "中篇";
			} else if (space_count == 3) {
				space = "长篇";
			}
			Book book = new Book(bookName, countryMapper.queryById(country_id), themeMapper.queryById(theme_id),
					typeMapper.queryById(type_id), space, bookCount, info, new Timestamp(new Date().getTime()));
			bookServiceImpl.addBook(book);
			wb.setData("");
			WriteBackUtil.setSuccess(wb);
			return wb;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(wb);
			return wb;
		}
	}

	@ApiOperation(notes = "删除一本书籍信息", value = "删除一本书籍信息")
	@DeleteMapping("/book/{id}")
	public WriteBack<String> delete(@PathVariable("id") Integer id) {
		WriteBack<String> wb = new WriteBack<>();
		try {
			bookServiceImpl.deleteById(id);
			WriteBackUtil.setSuccess(wb);
			wb.setData("");
			return wb;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(wb);
			return wb;
		}
	}

	@ApiOperation(notes = "更新一本书籍信息", value = "更新一本书籍信息")
	@PutMapping("/book")
	public WriteBack<String> update(@RequestBody Map<String, Object> requestMap) {
		int id = (int) requestMap.get("id");
		String bookName = (String) requestMap.get("bookName");
		int country_id = (int) requestMap.get("country_id");
		int theme_id = (int) requestMap.get("theme_id");
		int type_id = (int) requestMap.get("theme_id");
		int space_count = (int) requestMap.get("space_count");
		int bookCount = (int) requestMap.get("bookCount");
		String info = (String) requestMap.get("info");
		String space = null;
		WriteBack<String> wb = new WriteBack<>();
		try {
			if (space_count == 1) {
				space = "短篇";
			} else if (space_count == 2) {
				space = "中篇";
			} else if (space_count == 3) {
				space = "长篇";
			}
			Book book = bookMapper.queryById(id);
			book.setBookName(bookName);
			book.setCountry(countryMapper.queryById(country_id));
			book.setTheme(themeMapper.queryById(theme_id));
			book.setType(typeMapper.queryById(type_id));
			book.setSpace(space);
			book.setBookCount(bookCount);
			book.setInfo(info);
			bookServiceImpl.updateBook(book);
			WriteBackUtil.setSuccess(wb);
			wb.setData("");
			return wb;
		} catch (Exception e) {
			e.printStackTrace();
			WriteBackUtil.setFail(wb);
			return wb;
		}
	}
}
