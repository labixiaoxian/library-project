package com.wyu.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @since 2020/11/21
 * @author 李润东
 *
 */
public interface ExcelService {

	void ExcelImport(MultipartFile file);

	void ExcelExport(HttpServletResponse response) throws IOException;
}
