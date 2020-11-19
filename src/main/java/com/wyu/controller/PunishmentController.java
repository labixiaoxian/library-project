package com.wyu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wyu.entity.Punishment;
import com.wyu.service.PunishmentService;
import com.wyu.utils.WriteBackUtil;
import com.wyu.vo.WriteBack;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 *
 * @author 李达成
 * @since 2020/11/18
 *
 */
@Api(value = "惩罚模块", tags = { "用于惩罚的相关接口" })
@RestController
public class PunishmentController {
	@Autowired
	PunishmentService punishmentService;

	@ApiOperation(notes = "删除一条惩罚记录", value = "删除一条惩罚记录")
	@DeleteMapping("/punishment/{id}")
	public WriteBack<String> delete(@PathVariable("id") Integer id) {
		WriteBack<String> writeBack = new WriteBack<>();
		try {
			punishmentService.deleteById(id);
			WriteBackUtil.setSuccess(writeBack);
			return writeBack;
		} catch (Exception e) {
			// TODO: handle exception
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}
	}

	@ApiOperation(notes = "分页查询所有惩罚记录", value = "分页查询所有惩罚记录")
	@GetMapping("/punishment")
	public WriteBack<List<Punishment>> getPunishments(
			@ApiParam(name = "currentPage", value = "当前页码", required = true) @RequestParam("currentPage") int currentPage,
			@ApiParam(name = "pageSize", value = "一页显示数据的数量", required = true) @RequestParam("pageSize") int pageSize) {
		WriteBack<List<Punishment>> writeBack = new WriteBack<>();
		try {
			List<Punishment> list = punishmentService.queryAllPagination(currentPage, pageSize);
			writeBack.setData(list);
			WriteBackUtil.setSuccess(writeBack);
			return writeBack;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}
	}

	@ApiOperation(notes = "分页根据名字模糊查询惩罚记录", value = "分页根据名字模糊查询惩罚记录")
	@GetMapping("/punishment/query")
	public WriteBack<List<Punishment>> queryByName(@RequestParam("name") String name,
			@RequestParam("currentPage") Integer currentPage, @RequestParam("size") Integer size) {
		WriteBack<List<Punishment>> writeBack = new WriteBack<>();
		try {
			List<Punishment> list = punishmentService.FuzzyqueryByNickName(name, currentPage, size);
			writeBack.setData(list);
			WriteBackUtil.setSuccess(writeBack);
			return writeBack;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}
	}
}
