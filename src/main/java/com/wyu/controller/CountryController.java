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
import com.wyu.entity.Country;
import com.wyu.service.serviceImpl.CountryServiceImpl;
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
@Api(value="图书国家模块",tags = {"用于图书国家管理的相关接口"})
@RestController
public class CountryController {
	
	
	@Autowired
	private CountryServiceImpl countryServiceImpl;
	
	@ApiOperation(notes = "查询所有国家信息", value = "查询所有国家信息")
	@GetMapping("/country")
	public WriteBack<List<Country>> queryAll(){
		WriteBack<List<Country>> wb = new WriteBack<List<Country>>();
		try {
			List<Country> list = countryServiceImpl.queryAll();
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
	
	@ApiOperation(notes = "通过id查询国家信息", value = "通过id查询国家信息")
	@GetMapping("/country/{id}")
	public WriteBack<Country> queryById(@PathVariable("id")int id){
		WriteBack<Country> wb = new WriteBack<Country>();
		try {
			Country country = countryServiceImpl.queryById(id);
			wb.setData(country);
			WriteBackUtil.setSuccess(wb);
			return wb;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(wb);
			return wb;
		}
	}
	
	@ApiOperation(notes = "新增一个国家信息", value = "新增一个国家信息")
	@PostMapping("/country")
	public WriteBack<String> insert(@RequestBody String body){
		WriteBack<String> wb = new WriteBack<String>();
		Gson gson = new Gson();
		try {
			Country country = gson.fromJson(body, Country.class);
			countryServiceImpl.insert(country);;
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
	
	@ApiOperation(notes = "更新一个国家信息", value = "更新一个国家信息")
	@PutMapping("/country")
	public WriteBack<String> update(@RequestBody String body){
		WriteBack<String> wb = new WriteBack<String>();
		Gson gson = new Gson();
		try {
			Country country = gson.fromJson(body, Country.class);
			countryServiceImpl.update(country);
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
	
	@ApiOperation(notes = "通过id删除一个国家信息", value = "通过id删除一个国家信息")
	@DeleteMapping("/country/{id}")
	public WriteBack<String> delete(@PathVariable("id")int id){
		WriteBack<String> wb = new WriteBack<String>();
		try {
			countryServiceImpl.deleteById(id);
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
