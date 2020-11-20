package com.wyu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.wyu.entity.Theme;
import com.wyu.service.serviceImpl.ThemeServiceImpl;
import com.wyu.utils.WriteBackUtil;
import com.wyu.vo.WriteBack;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @since 2020/11/19
 * @author 李润东
 *
 */
@Api(value="图书主题模块",tags = {"用于图书主题管理的相关接口"})
@RestController
public class ThemeController {
	@Autowired
	private ThemeServiceImpl themeServiceImpl;
	
	@ApiOperation(notes = "查询所有主题信息", value = "查询所有主题信息")
	@GetMapping("/theme")
	public WriteBack<List<Theme>> queryAll(){
		WriteBack<List<Theme>> wb = new WriteBack<List<Theme>>();
		try {
			List<Theme> list = themeServiceImpl.queryAll();
			wb.setData(list);
			WriteBackUtil.setSuccess(wb);
			return wb;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(wb);
			return wb;
		}
	}
	
	@ApiOperation(notes = "通过id查询主题信息", value = "通过id查询主题信息")
	@GetMapping("/theme/{id}")
	public WriteBack<Theme> queryById(@PathVariable("id")int id){
		WriteBack<Theme> wb = new WriteBack<Theme>();
		try {
			Theme theme = themeServiceImpl.queryById(id);
			wb.setData(theme);
			WriteBackUtil.setSuccess(wb);
			return wb;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(wb);
			return wb;
		}
	}
	
	@ApiOperation(notes = "新增一个主题信息", value = "新增一个主题信息")
	@PostMapping("/theme")
	public WriteBack<String> insert(@RequestBody String body){
		WriteBack<String> wb = new WriteBack<String>();
		Gson gson = new Gson();
		try {
			Theme theme = gson.fromJson(body, Theme.class);
			themeServiceImpl.insertTheme(theme);
			wb.setData("");
			WriteBackUtil.setSuccess(wb);
			return wb;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(wb);
			return wb;
		}
	}
	
	@ApiOperation(notes = "更新一个主题信息", value = "更新一个主题信息")
	@PutMapping("/theme")
	public WriteBack<String> update(@RequestBody String body){
		WriteBack<String> wb = new WriteBack<String>();
		Gson gson = new Gson();
		try {
			Theme theme = gson.fromJson(body, Theme.class);
			themeServiceImpl.updateById(theme);
			wb.setData("");
			WriteBackUtil.setSuccess(wb);
			return wb;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(wb);
			return wb;
		}
	}
	
	@ApiOperation(notes = "通过id删除一个主题信息", value = "通过id删除一个主题信息")
	@DeleteMapping("/theme/{id}")
	public WriteBack<String> delete(@PathVariable("id")int id){
		WriteBack<String> wb = new WriteBack<String>();
		try {
			themeServiceImpl.deleteById(id);
			wb.setData("");
			WriteBackUtil.setSuccess(wb);
			return wb;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(wb);
			return wb;
		}
	}
	
}
