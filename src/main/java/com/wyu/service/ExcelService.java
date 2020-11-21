package com.wyu.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @since 2020/11/21
 * @author 李润东
 *
 */
public interface ExcelService {
	
	void ExcelImport(MultipartFile file);
	void ExcelExport()throws IOException;
}
