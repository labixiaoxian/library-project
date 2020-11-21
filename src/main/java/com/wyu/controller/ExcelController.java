package com.wyu.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wyu.service.serviceImpl.ExcelServiceImpl;

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
	
	@ApiOperation(notes = "导入数据", value = "导入数据")
	@PostMapping("/import")
	public void ExcelImport(@RequestParam("file")MultipartFile file) {
		excelServiceImpl.ExcelImport(file);
	}
	
	@ApiOperation(notes = "导出数据", value = "导出数据")
	@GetMapping("/export")
	public void ExcelExport() {
		try {
			excelServiceImpl.ExcelExport();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
