package com.wyu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.wyu.entity.Book;
import com.wyu.entity.BorrowInfo;
import com.wyu.service.BookService;
import com.wyu.service.BorrowInfoService;
import com.wyu.utils.WriteBackUtil;
import com.wyu.vo.WriteBack;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 *
 * @author 李达成
 * @since 2020/11/19
 */
@Api(value = "借阅信息模块", tags = { "借阅信息模块" })
@RestController
public class BorrowInfoController {

	@Autowired
	BorrowInfoService borrowInfoService;

	@Autowired
	BookService bookService;

	@ApiOperation(notes = "审核操作", value = "审核操作")
	@PutMapping("/borrowInfo/operate")
	public WriteBack<String> approve(
			@ApiParam(name = "body", value = "json格式数据，传入两个属性：operation(1位通过，2为拒绝)和id", required = true) @RequestBody String body) {
		WriteBack<String> writeBack = new WriteBack<>();
		Gson gson = new Gson();
		try {
			HashMap<String, Object> hashMap = gson.fromJson(body, HashMap.class);
			Integer operation = ((Double) hashMap.get("operation")).intValue();
			Integer id = ((Double) hashMap.get("id")).intValue();
			switch (operation) {
			case 1:
				try {
					borrowInfoService.approve(id);
					WriteBackUtil.setSuccess(writeBack);
					return writeBack;
				} catch (Exception e) {
					// TODO: handle exception
					WriteBackUtil.setFail(writeBack);
					return writeBack;
				}
			case 2:
				try {
					borrowInfoService.refuse(id);
					Book book = bookService.queryById(borrowInfoService.getById(id).getBookId());
					book.setBookCount(book.getBookCount() + 1);
					bookService.updateBook(book);
					WriteBackUtil.setSuccess(writeBack);
					return writeBack;
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					WriteBackUtil.setFail(writeBack);
					return writeBack;
				}
			default:
				WriteBackUtil.setFail(writeBack);
				return writeBack;
			}
		} catch (Exception e) {
			// TODO: handle exception
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}
	}

	@ApiOperation(notes = "插入一条借阅记录", value = "插入一条借阅记录")
	@PostMapping("/borrowInfo")
	public WriteBack<String> borrowBook(
			@ApiParam(name = "body", value = "json格式数据，传入两个属性：userId和bookId", required = true) @RequestBody String body) {
		WriteBack<String> writeBack = new WriteBack<>();
		Gson gson = new Gson();
		try {
			HashMap<String, Object> hashMap = gson.fromJson(body, HashMap.class);
			Integer userId = ((Double) hashMap.get("userId")).intValue();
			Integer bookId = ((Double) hashMap.get("bookId")).intValue();
			if (borrowInfoService.getCountByUserId(userId) >= 3) {
				throw new Exception();
			}
			BorrowInfo borrowInfo = new BorrowInfo();
			borrowInfo.setBookId(bookId);
			borrowInfo.setUserId(userId);
			borrowInfoService.insert(borrowInfo);
			WriteBackUtil.setSuccess(writeBack);
			return writeBack;
		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
			WriteBackUtil.setWriteBack(500, "借阅失败，图书数量不足或没有借阅次数", "", writeBack);
			return writeBack;
		}
	}

	@ApiOperation(notes = "删除某一条借阅记录", value = "删除某一条借阅记录")
	@DeleteMapping("/borrowInfo/{id}")
	public WriteBack<String> deleteBorrowInfo(@PathVariable("id") Integer id) {
		WriteBack<String> writeBack = new WriteBack<>();
		try {
			BorrowInfo borrowInfo = borrowInfoService.getById(id);
			if (borrowInfo.getBorrowState() != 0) {
				throw new Exception();
			}
			borrowInfoService.delete(id);
			WriteBackUtil.setSuccess(writeBack);
			return writeBack;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}
	}

	@ApiOperation(notes = "查询某用户的书架", value = "查询某用户的书架")
	@GetMapping("/borrowInfo/bookShelf")
	public WriteBack<List<BorrowInfo>> getBookShelf(
			@ApiParam(name = "userId", value = "用户ID", required = true) @RequestParam("userId") Integer userId) {
		WriteBack<List<BorrowInfo>> writeBack = new WriteBack<>();
		try {
			WriteBackUtil.setWriteBack(200, "查询书架成功", borrowInfoService.getBookShelf(userId), writeBack);
			return writeBack;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}
	}

	@ApiOperation(notes = "查询某用户借阅的书籍数", value = "查询某用户借阅的书籍数")
	@GetMapping("/borrowInfo/borrowCount")
	public WriteBack<Integer> getBorrowCount(@RequestParam("id") Integer userId) {
		WriteBack<Integer> writeBack = new WriteBack<>();
		try {
			writeBack.setData(borrowInfoService.getCountByUserId(userId));
			WriteBackUtil.setSuccess(writeBack);
			return writeBack;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}
	}

	@ApiOperation(notes = "分页查询所有用户借阅信息", value = "分页查询所有用户借阅信息")
	@GetMapping("/borrowInfo")
	public WriteBack<List<BorrowInfo>> getBorrowInfos(@RequestParam("currentPage") Integer currentPage,
			@RequestParam("pageSize") Integer pageSize) {
		WriteBack<List<BorrowInfo>> writeBack = new WriteBack<>();
		try {
			writeBack.setCount(borrowInfoService.getBorrowInfosCount());
			WriteBackUtil.setWriteBack(200, "查询借阅信息成功",
					borrowInfoService.getBorrowInfosPagination(currentPage, pageSize), writeBack);
			return writeBack;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}
	}

	@ApiOperation(notes = "根据借阅状态分页查询借阅信息", value = "根据借阅状态分页查询借阅信息")
	@GetMapping("/borrowInfo/borrow")
	public WriteBack<List<BorrowInfo>> getBorrowingBorrowInfos(@RequestParam("states") Integer states,
			@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize) {
		WriteBack<List<BorrowInfo>> writeBack = new WriteBack<>();
		try {
			writeBack.setCount(borrowInfoService.getByBorrowStateCount(states));
			WriteBackUtil.setWriteBack(200, "查询审核中的借阅记录成功",
					borrowInfoService.getByBorrowStatesPagination(states, currentPage, pageSize), writeBack);
			return writeBack;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}
	}

	@ApiOperation(notes = "根据书的id查询该书籍的借阅记录", value = "根据书的id查询该书籍的借阅记录")
	@GetMapping("/borrowInfo/bookHistory")
	public WriteBack<List<BorrowInfo>> getByBookId(@RequestParam("bookId") Integer bookId) {
		WriteBack<List<BorrowInfo>> writeBack = new WriteBack<>();
		try {
			writeBack.setData(borrowInfoService.getBorrowInfosByBookId(bookId));
			WriteBackUtil.setSuccess(writeBack);
			return writeBack;
		} catch (Exception e) {
			// TODO: handle exception
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}
	}

	@ApiOperation(notes = "分页查询用户借阅历史", value = "分页查询用户借阅历史")
	@GetMapping("/borrowInfo/userHistory")
	public WriteBack<List<BorrowInfo>> getUserHistoryBorrowInfo(@RequestParam("userId") Integer userId,
			@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize) {
		WriteBack<List<BorrowInfo>> writeBack = new WriteBack<>();
		try {
			writeBack.setCount(borrowInfoService.getByUserIdCount(userId));
			WriteBackUtil.setWriteBack(200, "查询用户历史借阅记录成功",
					borrowInfoService.getUserBorrowHistory(userId, currentPage, pageSize), writeBack);
			;
			return writeBack;
		} catch (Exception e) {
			// TODO: handle exception
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}
	}

	@ApiOperation(notes = "续借书", value = "续借书")
	@PutMapping("/borrowInfo/renewBook")
	public WriteBack<String> renewBook(
			@ApiParam(name = "id", value = "借阅信息ID", required = true) @RequestParam("id") Integer id) {
		WriteBack<String> writeBack = new WriteBack<>();
		try {
			if (borrowInfoService.getById(id).getRenewState() == 0) {
				borrowInfoService.renewBook(id);
			} else {
				throw new Exception("已续借过，无法续借");
			}
			WriteBackUtil.setSuccess(writeBack);
			return writeBack;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}
	}

	@ApiOperation(notes = "还书", value = "还书")
	@PutMapping("/borrowInfo/returnBook")
	public WriteBack<String> returnBook(@ApiParam(name = "id", value = "借阅信息ID") Integer id) {
		WriteBack<String> writeBack = new WriteBack<>();
		try {
			borrowInfoService.returnBook(id);
			WriteBackUtil.setSuccess(writeBack);
			return writeBack;
		} catch (Exception e) {
			// TODO: handle exception
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}
	}

	@ApiOperation(notes = "根据用户姓名和借阅状态分页查询借阅信息", value = "根据用户姓名和借阅状态分页查询借阅信息")
	@GetMapping("/borrowInfo/fuzzyQuery")
	public WriteBack<List<BorrowInfo>> getByNickNameAndStates(@RequestParam("name") String name,
			@RequestParam("states") Integer states, @RequestParam("currentPage") Integer currentPage,
			@RequestParam("pageSize") Integer pageSize) {
		WriteBack<List<BorrowInfo>> writeBack = new WriteBack<>();
		try {
			writeBack.setCount(borrowInfoService.getByNameAndStatesCount(name, states));
			writeBack.setData(borrowInfoService.getByNameAndStatesPagination(name, states, currentPage, pageSize));
			WriteBackUtil.setSuccess(writeBack);
			return writeBack;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			WriteBackUtil.setFail(writeBack);
			return writeBack;
		}
	}

	@ApiOperation(value = "统计接口", notes = "统计接口")
	@GetMapping("/borrowInfo/statistics")
	public WriteBack<Map<String, Integer>> statistics() {
		WriteBack<Map<String, Integer>> writeBack = new WriteBack<>();
		HashMap<String, Integer> hashMap = new HashMap<>();
		try {
			hashMap.put("borrowToday", borrowInfoService.numOfBorrowingToday());
			hashMap.put("borrowThisMonth", borrowInfoService.numOfBorrowingThisMonth());
			hashMap.put("borrowThisYear", borrowInfoService.numOfBorrowingThisYear());
			hashMap.put("overdue", borrowInfoService.getOverDueCount());
			hashMap.put("reviewing", borrowInfoService.getByBorrowStateCount(0));
			hashMap.put("borrowing", borrowInfoService.getByBorrowStateCount(1));
			writeBack.setData(hashMap);
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
