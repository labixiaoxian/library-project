package com.wyu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wyu.entity.Punishment;
import com.wyu.service.PunishmentService;
import com.wyu.utils.WriteBackUtil;
import com.wyu.vo.WriteBack;

import io.swagger.annotations.Api;

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

	@GetMapping("/punishment")
	public WriteBack<List<Punishment>> getPunishments(int currentPage, int size) {
		WriteBack<List<Punishment>> writeBack = new WriteBack<>();
		try {
			List<Punishment> list = punishmentService.queryAllPagination(currentPage, size);
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

	@GetMapping("/punishment/query")
	public WriteBack<List<Punishment>> queryByName(HttpServletRequest request) {
		WriteBack<List<Punishment>> writeBack = new WriteBack<>();
		String name = request.getParameter("name");
		int currentPage = Integer.valueOf(request.getParameter("currentPage"));
		int size = Integer.valueOf(request.getParameter("size"));
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
