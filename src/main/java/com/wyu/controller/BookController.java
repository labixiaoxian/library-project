package com.wyu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wyu.entity.Book;
import com.wyu.service.serviceImpl.BookServiceImpl;
import com.wyu.vo.WriteBack;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @since 2020/11/18
 * @author 李润东
 */
@Api(value="图书模块",tags = {"用于图片的相关接口"})
@RestController
public class BookController {
	@Autowired
	private BookServiceImpl bookServiceImpl;
	
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
	
	@ApiOperation(notes = "查询全部图书信息（分页）",value = "查询全部图书信息（分页）")
	@GetMapping("/book")
	public WriteBack<Object> queryAllDivPage(
            @ApiParam(name = "currentPage", value = "当前页码", required = true) @RequestParam("currentPage") int currentPage,
            @ApiParam(name = "pageSize", value = "一页显示数据的数量", required = true) @RequestParam("pageSize") int pageSize){
		List<Book> list = bookServiceImpl.queryAllDivPage(currentPage, pageSize);
		WriteBack<Object> wb = new WriteBack<Object>();
		wb.setCode(369);
		wb.setData(list);
		wb.setMsg("查询所有分页");
		return wb;
	}
	
	@ApiOperation(notes = "查询全部图书信息（模糊查询）（分页）",value = "查询全部图书信息（模糊查询）（分页）")
	@GetMapping("/book/query")
	public WriteBack<Object> queryAllByLike(HttpServletRequest request){
		String nickname = request.getParameter("nickname");
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int country_id =Integer.parseInt(request.getParameter("country_id")) ;
		int theme_id = Integer.parseInt(request.getParameter("theme_id")) ;
		int type_id = Integer.parseInt(request.getParameter("type_id"));
		String space = request.getParameter("space");
		List<Book> list = bookServiceImpl.queryLikeNameDivPage(nickname, country_id, theme_id, type_id, space, currentPage, pageSize);
		WriteBack<Object> wb = new WriteBack<Object>();
		wb.setCode(333);
		wb.setData(list);
		wb.setMsg("模糊查询分页");
		return wb;
	}
}











