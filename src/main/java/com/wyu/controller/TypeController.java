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
import com.wyu.entity.Type;
import com.wyu.service.serviceImpl.TypeServiceImpl;
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
@Api(value="图书类型模块",tags = {"用于图书类型管理的相关接口"})
@RestController
public class TypeController {
	@Autowired
	private TypeServiceImpl typeServiceImpl;
	
	@ApiOperation(notes = "查询全部类型信息", value = "查询全部类型信息")
	@GetMapping("/type")
	public WriteBack<List<Type>> queryAll(){
		WriteBack<List<Type>> wb = new WriteBack<List<Type>>();
		try {
			List<Type> list = typeServiceImpl.queryAll();
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
	
	@ApiOperation(notes = "通过id查询类型信息", value = "通过id查询类型信息")
	@GetMapping("/type/{id}")
	public WriteBack<Type> queryById(@PathVariable("id")int id){
		WriteBack<Type> wb = new WriteBack<Type>();
		try {
			Type type = typeServiceImpl.queryById(id);
			wb.setData(type);
			WriteBackUtil.setSuccess(wb);
			return wb;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(wb);
			return wb;
		}
	}
	
	@ApiOperation(notes = "添加类型信息", value = "添加类型信息")
	@PostMapping("/type")
	public WriteBack<String> insert(@RequestBody String body){
		WriteBack<String> wb = new WriteBack<String>();
		Gson gson = new Gson();
		try {
			Type type = gson.fromJson(body, Type.class);
			typeServiceImpl.insertType(type);
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
	
	@ApiOperation(notes = "更新一个类型信息", value = "更新一个类型信息")
	@PutMapping("/type")
	public WriteBack<String> update(@RequestBody String body){
		WriteBack<String> wb = new WriteBack<String>();
		Gson gson = new Gson();
		try {
			Type type = gson.fromJson(body, Type.class);
			typeServiceImpl.updateById(type);
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
	
	@ApiOperation(notes = "通过id删除一个类型信息", value = "通过id删除一个类型信息")
	@DeleteMapping("/type/{id}")
	public WriteBack<String> delete(@PathVariable("id")int id){
		WriteBack<String> wb = new WriteBack<String>();
		try {
			typeServiceImpl.deleteById(id);
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
