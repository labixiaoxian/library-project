package com.wyu.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by XiaoXian on 2020/11/19.
 */
@Configuration
public class ImageUtil {

    private static String imagePath;

    @Value("${upload.imagePath}")
    public  void setImagePath(String imagePath) {
        ImageUtil.imagePath = imagePath;
    }

    public static String uploadPath(String fileName){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = sdf.format(new Date());
        String path = imagePath + File.separator + format + "_" + fileName;
        return path;
    }
}
