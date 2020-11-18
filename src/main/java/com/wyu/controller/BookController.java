package com.wyu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wyu.entity.Book;
import com.wyu.service.serviceImpl.BookServiceImpl;
import com.wyu.vo.WriteBack;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @since 2020/11/18
 * @author 李润东
 */
@Api(value="图书模块",tags = {"用于图片的相关接口"})
@RestController
public class BookController {
	@Autowired
	private BookServiceImpl bookServiceImpl;
	
	@ApiOperation(notes = "查询全部图书信息",value = "查询全部图书信息")
	@GetMapping("/book/query")
	public WriteBack<Object> queryAll() {
		List<Book> list = bookServiceImpl.queryAll();
		WriteBack<Object> wb = new WriteBack<Object>();
		wb.setCode(4396);
		wb.setData(list);
		wb.setMsg("查询所有");
		return wb;
	}
	
//	@ApiOperation(notes = "查询全部图书信息（分页）",value = "查询全部图书信息（分页）")
//	@GetMapping("/queryAllPage")
//	public WriteBack<Object> queryAllDivPage(@ApiParam(name = "name", value = "图书（模糊或准确皆可）", required = true) @RequestParam("name") String name,
//            @ApiParam(name = "currentPage", value = "当前页码", required = true) @RequestParam("currentPage") int currentPage,
//            @ApiParam(name = "pageSize", value = "一页显示数据的数量", required = true) @RequestParam("pageSize") int pageSize){
//		WriteBack<Object> wb = new WriteBack<Object>();
//		
//		return wb;
//	}
}











