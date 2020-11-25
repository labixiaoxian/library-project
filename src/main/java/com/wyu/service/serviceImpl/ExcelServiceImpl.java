package com.wyu.service.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wyu.dto.BookDto;
import com.wyu.entity.Book;
import com.wyu.service.ExcelService;
import com.wyu.utils.Download;
import com.wyu.utils.ExcelUtil;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;

/**
 * 
 * @since 2020/11/21
 * @author 李润东
 *
 */
@Service
public class ExcelServiceImpl implements ExcelService {

	@Autowired
	private BookServiceImpl bookServiceImpl;

	@Override
	public void ExcelImport(MultipartFile file) {
		ImportParams params = new ImportParams();
		params.setTitleRows(1);
		params.setHeadRows(1);
		List<BookDto> list = null;
		String path = ExcelUtil.uploadPath(file.getOriginalFilename());
		File saveFile = new File(path);
		if (!saveFile.exists()) {
			saveFile.mkdirs();
		}
		try {
			file.transferTo(saveFile);
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			list = ExcelImportUtil.importExcel(new FileInputStream(path), BookDto.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bookServiceImpl.insertBookImport(list);
	}

	@Override
	public void ExcelExport(HttpServletResponse response) throws IOException {
		// 获取路径
		String path = ExcelUtil.exportPath();
		// 获取数据
		List<Book> list = bookServiceImpl.queryAll();
		System.out.println(list);
		File saveFile = new File(path);
		if (!saveFile.exists()) {
			saveFile.mkdirs();
		}

		// 导出数据
		// 参数1:ExportParams导出配置对象 参数2:导出类型 参数3:导出的数据
		Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("图书信息列表", "图书信息"), Book.class, list);
		FileOutputStream stream;
		try {
			stream = new FileOutputStream(path + "/导出图书信息.xls");
			workbook.write(stream);
			Download.download("导出图书信息.xls", path + "/导出图书信息.xls", response);
			stream.close();
			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
