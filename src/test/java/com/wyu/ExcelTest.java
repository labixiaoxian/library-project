package com.wyu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wyu.dao.BookMapper;
import com.wyu.dto.BookDto;
import com.wyu.entity.Book;
import com.wyu.service.serviceImpl.BookServiceImpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;

@SpringBootTest
public class ExcelTest {
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private BookServiceImpl bookServiceImpl;
	
	@Test
	public void ExcelTestExport() throws IOException{
		//获取数据
		List<Book> list = bookMapper.queryAll();
		//导出数据
		//参数1:ExportParams导出配置对象        参数2:导出类型         参数3:导出的数据
		Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("图书信息列表","图书信息"),Book.class, list);
		FileOutputStream stream = new FileOutputStream("C:/Users/Administrator/Desktop/book.xls");
		workbook.write(stream);
		stream.close();
		workbook.close();
	}
	
	@Test
	public void ExcelTestImport() throws FileNotFoundException, Exception {
		ImportParams params = new ImportParams();
		//params.setTitleRows(0);
		params.setHeadRows(1);
		List<BookDto> list = ExcelImportUtil.importExcel(new FileInputStream("C:/Users/Administrator/Desktop/daoru.xls"), BookDto.class, params);
		
		bookServiceImpl.insertBookImport(list);
	}
}
