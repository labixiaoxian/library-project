package com.wyu.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.wyu.dao.BorrowInfoMapper;
import com.wyu.entity.BorrowInfo;
import com.wyu.service.BorrowInfoService;
import com.wyu.utils.WriteBackUtil;
import com.wyu.vo.WriteBack;

/**
 *
 * @author 李达成
 * @since 2020/11/19
 */
@RestController
public class BorrowInfoController {

    @Autowired
    BorrowInfoService borrowInfoService;

    @Autowired
    BorrowInfoMapper borrowInfoMapper;

    @PutMapping("/borrowInfo/approve")
    public WriteBack<String> approve(Integer id) {
        WriteBack<String> writeBack = new WriteBack<>();
        try {
            borrowInfoService.approve(id);
            WriteBackUtil.setSuccess(writeBack);
            return writeBack;
        } catch (Exception e) {
            // TODO: handle exception
            WriteBackUtil.setFail(writeBack);
            return writeBack;
        }
    }

    @PostMapping("/borrowInfo")
    public WriteBack<String> borrowBook(@RequestBody String body) {
        WriteBack<String> writeBack = new WriteBack<>();
        Gson gson = new Gson();
        try {
            HashMap<String, Object> hashMap = gson.fromJson(body, HashMap.class);
            Integer userId = ((Double) hashMap.get("userId")).intValue();
            Integer bookId = ((Double) hashMap.get("bookId")).intValue();
            if (borrowInfoService.getCountByUserId(userId) == 200) {
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
            WriteBackUtil.setWriteBack(500, "借阅失败，图书数量不足或没有借阅次数", "", writeBack);
            return writeBack;
        }
    }

    @GetMapping("/borrowInfo/bookShelf")
    public WriteBack<List<BorrowInfo>> getBookShelf(Integer userId) {
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

    @GetMapping("/borrowInfo/count")
    public WriteBack<Integer> getBorrowCount(@RequestParam("id") Integer id) {
        WriteBack<Integer> writeBack = new WriteBack<>();
        try {
            writeBack.setData(borrowInfoService.getCountByUserId(id));
            WriteBackUtil.setSuccess(writeBack);
            return writeBack;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            WriteBackUtil.setFail(writeBack);
            return writeBack;
        }
    }

    @GetMapping("/borrowInfo")
    public WriteBack<List<BorrowInfo>> getBorrowInfos(@RequestParam("currentPage") Integer currentPage,
            @RequestParam("pageSize") Integer pageSize) {
        WriteBack<List<BorrowInfo>> writeBack = new WriteBack<>();
        try {
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

    @GetMapping("/borrowInfo/borrow")
    public WriteBack<List<BorrowInfo>> getBorrowingBorrowInfos(@RequestParam("currentPage") Integer currentPage,
            @RequestParam("pageSize") Integer pageSize) {
        WriteBack<List<BorrowInfo>> writeBack = new WriteBack<>();
        try {
            WriteBackUtil.setWriteBack(200, "查询审核中的借阅记录成功",
                    borrowInfoService.getBorrowingBorrowInfos(currentPage, pageSize), writeBack);
            return writeBack;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            WriteBackUtil.setFail(writeBack);
            return writeBack;
        }
    }

    @GetMapping("/borrowInfo/Finished")
    public WriteBack<List<BorrowInfo>> getFinishedBorrowInfos(@RequestParam("currentPage") Integer currentPage,
            @RequestParam("pageSize") Integer pageSize) {
        WriteBack<List<BorrowInfo>> writeBack = new WriteBack<>();
        try {
            WriteBackUtil.setWriteBack(200, "查询审核中的借阅记录成功",
                    borrowInfoService.getFinishedBorrowInfos(currentPage, pageSize), writeBack);
            return writeBack;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            WriteBackUtil.setFail(writeBack);
            return writeBack;
        }
    }

    @GetMapping("/borrowInfo/reviewing")
    public WriteBack<List<BorrowInfo>> getReviewingBorrowInfos(@RequestParam("currentPage") Integer currentPage,
            @RequestParam("pageSize") Integer pageSize) {
        WriteBack<List<BorrowInfo>> writeBack = new WriteBack<>();
        try {
            WriteBackUtil.setWriteBack(200, "查询审核中的借阅记录成功",
                    borrowInfoService.getReviewingBorrowInfos(currentPage, pageSize), writeBack);
            return writeBack;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            WriteBackUtil.setFail(writeBack);
            return writeBack;
        }
    }

    @GetMapping("/borrowInfo/history")
    public WriteBack<List<BorrowInfo>> getUserHistoryBorrowInfo(@RequestParam("userId") Integer userId,
            @RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize) {
        WriteBack<List<BorrowInfo>> writeBack = new WriteBack<>();
        try {
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

    @PutMapping("/borrowInfo/renewBook")
    public WriteBack<String> renewBook(Integer id) {
        WriteBack<String> writeBack = new WriteBack<>();
        try {
            if (borrowInfoMapper.getById(id).getRenewState() == 0) {
                borrowInfoService.returnBook(id);
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

    @PutMapping("/borrowInfo/returnBook")
    public WriteBack<String> returnBook(Integer id) {
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
}
