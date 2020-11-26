package com.wyu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wyu.entity.Book;
import com.wyu.service.serviceImpl.BookServiceImpl;
import com.wyu.service.serviceImpl.ExcelServiceImpl;
import com.wyu.utils.HttpServletRequestUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @since 2020/11/21
 * @author 李润东
 *
 */
@Api(value = "Excel导出导入模块", tags = { "用于Excel的相关接口" })
@RestController
public class ExcelController {
	@Autowired
	private ExcelServiceImpl excelServiceImpl;

	@Autowired
	private BookServiceImpl bookServiceImpl;

	@ApiOperation(notes = "导入数据", value = "导入数据")
	@PostMapping("/import")
	public void ExcelImport(@RequestParam("file") MultipartFile file) {
		excelServiceImpl.ExcelImport(file);
	}

	@ApiOperation(notes = "导出数据", value = "导出数据")
	@RequestMapping("/export")
	public void ExcelExport(HttpServletResponse response, HttpServletRequest request) {
		Integer country_id = HttpServletRequestUtil.getInt(request, "country_id");
		Integer theme_id = HttpServletRequestUtil.getInt(request, "theme_id");
		Integer type_id = HttpServletRequestUtil.getInt(request, "type_id");
		Integer space_count = HttpServletRequestUtil.getInt(request, "space");
		String name = HttpServletRequestUtil.getString(request, "name");
		String space = null;
		if (!ObjectUtils.isEmpty(space_count)) {
			if (space_count == 1) {
				space = "短篇";
			} else if (space_count == 2) {
				space = "中篇";
			} else if (space_count == 3) {
				space = "长篇";
			}
		}
		List<Book> list = bookServiceImpl.queryLikeName(name, country_id, theme_id, type_id, space);
		try {
			excelServiceImpl.ExcelExport(response, list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
