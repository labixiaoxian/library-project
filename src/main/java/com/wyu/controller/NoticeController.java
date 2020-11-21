package com.wyu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.wyu.entity.Notice;
import com.wyu.service.serviceImpl.NoticeServiceImpl;
import com.wyu.utils.WriteBackUtil;
import com.wyu.vo.WriteBack;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "公告模块", tags = { "用于公告的相关接口" })
@RestController
public class NoticeController {
	@Autowired
	NoticeServiceImpl service;

	@ApiOperation(notes = "模糊分页查询", value = "模糊查询")
	@GetMapping("/notice/query")
	public WriteBack<List<Notice>> fuzzyQuery(
			@ApiParam(name = "name", value = "公告标题（模糊或准确皆可）", required = true) @RequestParam("name") String name,
			Integer currentPage, Integer pageSize) {
		WriteBack<List<Notice>> writeBack = new WriteBack<>();
		try {
			writeBack.setData(service.fuzzyQueryPagination(name, currentPage, pageSize));
			writeBack.setCount(service.fuzzyQueryBynameCount("%" + name + "%"));
			WriteBackUtil.setSuccess(writeBack);
			return writeBack;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}
	}

	@ApiOperation(notes = "分页查询所有数据", value = "分页查询所有数据")
	@GetMapping("/notice")
	public WriteBack<List<Notice>> getAll(Integer currentPage, Integer pageSize) {
		WriteBack<List<Notice>> writeBack = new WriteBack<>();
		try {
			List<Notice> dataList = service.queryAllPagination(currentPage, pageSize);
			writeBack.setCount(service.listCount());
			writeBack.setData(dataList);
			WriteBackUtil.setSuccess(writeBack);
			return writeBack;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}
	}

	@ApiOperation(notes = "查询最新公告（时间上最新）", value = "查询最新公告（时间上最新）")
	@GetMapping("/notice/latest")
	public WriteBack<Notice> getLatest() {
		WriteBack<Notice> writeBack = new WriteBack<>();
		try {
			writeBack.setData(service.getLatestNotice());
			WriteBackUtil.setSuccess(writeBack);
			return writeBack;
		} catch (Exception e) {
			// TODO: handle exception
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}
	}

	@ApiOperation(notes = "根据公告ID查公告", value = "根据公告ID查公告")
	@GetMapping("/notice/{id}")
	public WriteBack<Notice> getOne(
			@ApiParam(name = "id", value = "公告ID", required = true) @PathVariable("id") Integer id) {
		WriteBack<Notice> writeBack = new WriteBack<>();
		try {
			Notice notice = service.get(id);
			WriteBackUtil.setSuccess(writeBack);
			writeBack.setData(notice);
			return writeBack;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}

	}

	@ApiOperation(notes = "增加一条公告", value = "增加一条公告")
	@PostMapping("/notice")
	public WriteBack<String> insert(@ApiParam(name = "json", value = "json的属性要有"
			+ "noticeName和content", required = true) @RequestBody String body) {
		WriteBack<String> writeBack = new WriteBack<>();
		writeBack.setData("");
		Gson gson = new Gson();
		try {
			Notice notice = gson.fromJson(body, Notice.class);
			notice.setId(null);
			notice.setReleaseTime(null);
			service.insert(notice);
			WriteBackUtil.setSuccess(writeBack);
			return writeBack;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}
	}

	@ApiOperation(notes = "更新一条公告", value = "更新一条公告")
	@PutMapping("/notice")
	public WriteBack<Notice> update(@ApiParam(name = "json", value = "{" + "'id':id" + "'noticeName':'name',"
			+ "'content':'myContent'}", required = true) @RequestBody String body) {
		WriteBack<Notice> writeBack = new WriteBack<>();
		Gson gson = new Gson();
		try {
			Notice notice = gson.fromJson(body, Notice.class);
			service.update(notice);
			WriteBackUtil.setSuccess(writeBack);
			writeBack.setData(service.get(notice.getId()));
			return writeBack;
		} catch (Exception e) {
			// TODO: handle exception
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}
	}

	@ApiOperation(notes = "删除一条公告", value = "删除一条公告")
	@DeleteMapping("/notice/{id}")
	public WriteBack<String> delete(@PathVariable("id") Integer id) {
		WriteBack<String> writeBack = new WriteBack<>();
		try {
			service.delete(id);
			WriteBackUtil.setSuccess(writeBack);
			return writeBack;
		} catch (Exception e) {
			// TODO: handle exception
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}
	}
}
