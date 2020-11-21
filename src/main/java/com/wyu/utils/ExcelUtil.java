package com.wyu.utils;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcelUtil {
	private static String excelPath;
	private static String excelExportPath;

    @Value("${upload.excelPath}")
    public  void setexcelPath(String excelPath) {
    	ExcelUtil.excelPath = excelPath;
    }
    @Value("${export.excelPath}")
    public  void setexcelExportPath(String excelExportPath) {
    	ExcelUtil.excelExportPath = excelExportPath;
    }

    public static String uploadPath(String fileName){
        String path = excelPath +File.separator+ fileName;
        return path;
    }
    public static String exportPath(){
        String path = excelExportPath +File.separator;
        return path;
    }
}
