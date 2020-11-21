package com.wyu.service.serviceImpl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.wyu.dao.BookMapper;
import com.wyu.dao.BorrowInfoMapper;
import com.wyu.dao.UserInfoMapper;
import com.wyu.dao.UserMapper;
import com.wyu.entity.Book;
import com.wyu.entity.BorrowInfo;
import com.wyu.entity.User;
import com.wyu.service.BorrowInfoService;

/**
 *
 * @author 李达成
 * @since 2020/11/19
 *
 */
@Service
public class BorrowInfoServiceImpl extends BorrowInfoService {

    @Autowired
    BorrowInfoMapper borrowInfoMapper;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    SqlSessionFactory sessionFactory;

    Lock lock = new ReentrantLock(true);

    /**
     * @param id
     */
    @Override
    public void approve(Integer id) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 30);
        BorrowInfo borrowInfo = borrowInfoMapper.getById(id);
        borrowInfo.setBorrowState(1);
        borrowInfo.setBorrowDate(new Timestamp(System.currentTimeMillis()));
        borrowInfo.setReturnDate(new Timestamp(cal.getTime().getTime()));
        borrowInfoMapper.update(borrowInfo);
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        BorrowInfo borrowInfo = borrowInfoMapper.getById(id);
        Book book = bookMapper.queryById(borrowInfo.getBookId());
        book.setBookCount(book.getBookCount() + 1);
        bookMapper.updateBook(book);
        borrowInfoMapper.deleteById(id);
    }

    /**
     * @apiNote 查询用户书架
     * @param userId
     * @return
     */
    @Override
    public List<BorrowInfo> getBookShelf(Integer userId) {
        List<BorrowInfo> data = borrowInfoMapper.getBookShelf(userId);
        relatedQuery(data);
        return data;
    }

    @Override
    public List<BorrowInfo> getBorrowInfosByBookId(Integer bookId) {
        // TODO Auto-generated method stub
        List<BorrowInfo> data = borrowInfoMapper.getBorrowInfosByBookId(bookId);
        relatedQuery(data);
        return data;
    }

    /**
     * @apiNote 分页查询借阅信息
     * @param currentPage,pageSize
     * @return
     */
    @Override
    public List<BorrowInfo> getBorrowInfosPagination(Integer currentPage, Integer pageSize) {
        List<BorrowInfo> data = borrowInfoMapper.getBorrowInfosPagination((currentPage - 1) * pageSize, pageSize);
        relatedQuery(data);
        return data;
    }

    @Override
    public List<BorrowInfo> getByBorrowStatesPagination(Integer states, Integer currentPage, Integer pageSize) {
        // TODO Auto-generated method stub
        List<BorrowInfo> data = borrowInfoMapper.getByBorrowStatePagination(states, (currentPage - 1) * pageSize,
                pageSize);
        relatedQuery(data);
        return data;
    }

    @Override
    public int getCountByUserId(Integer id) {
        return userMapper.findUserById(id).getBorrowCount();
    }

    /**
     * @apiNote 分页获得用户所有的借阅记录
     * @param userId
     * @return
     */
    @Override
    public List<BorrowInfo> getUserBorrowHistory(Integer userId, Integer currentPage, Integer pageSize) {
        List<BorrowInfo> data = borrowInfoMapper.getByUserIdPagination(userId, (currentPage - 1) * pageSize, pageSize);
        relatedQuery(data);
        return data;
    }

    /**
     * @param borrowInfo
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void insert(BorrowInfo borrowInfo) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 30);
        borrowInfo.setExamineState(0);
        borrowInfo.setBorrowState(0);
        borrowInfo.setRenewState(0);
        Book book = bookMapper.queryById(borrowInfo.getBookId());
        // 双重校验模式，保证多线程环境下该方法的安全
        if (book.getBookCount() != 0) {
            lock.lock();
            if (book.getBookCount() != 0) {
                lock.lock();
                try {
                    User user = userMapper.findUserById(borrowInfo.getUserId());
                    int borrowCount = user.getBorrowCount();
                    if (borrowCount >= 3) {
                        throw new Exception("借书数量超过限制");
                    }
                    user.setBorrowCount(user.getBorrowCount() + 1);
                    userMapper.updateUser(user);
                    book.setBookCount(book.getBookCount() - 1);
                    System.out.println(book.getBookCount());
                    bookMapper.updateBook(book);
                    borrowInfo.setBorrowDate(new Timestamp(System.currentTimeMillis()));
                    borrowInfo.setReturnDate(new Timestamp(cal.getTime().getTime()));
                    borrowInfoMapper.insert(borrowInfo);
                } finally {
                    lock.unlock();
                    lock.unlock();
                }
            } else {
                lock.unlock();
                throw new Exception();
            }
        } else {
            throw new Exception("书的数量为0");
        }

    }

    @Override
    public void refuse(Integer id) {
        // TODO Auto-generated method stub
        BorrowInfo info = borrowInfoMapper.getById(id);
        info.setBorrowState(-1);
        info.setExamineState(-1);
        borrowInfoMapper.update(info);
    }

    /**
     *
     * @param data
     */
    private void relatedQuery(List<BorrowInfo> data) {
        for (BorrowInfo info : data) {
            info.setBook(bookMapper.queryById(info.getBookId()));
            info.setUserInfo(userInfoMapper.findUserInfoByUserId(info.getUserId()));
        }
    }

    @Override
    public void renewBook(Integer id) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 30);
        BorrowInfo borrowInfo = borrowInfoMapper.getById(id);
        if (borrowInfo.getBorrowState() != 1) {
            throw new Exception();
        }
        borrowInfo.setBorrowDate(new Timestamp(System.currentTimeMillis()));
        borrowInfo.setReturnDate(new Timestamp(cal.getTime().getTime()));
        borrowInfo.setRenewState(1);
        borrowInfoMapper.update(borrowInfo);
    }

    @Override
    public void returnBook(Integer id) {
        borrowInfoMapper.updateStates(id, 2);
        BorrowInfo borrowInfo = borrowInfoMapper.getById(id);
        User user = userMapper.findUserById(borrowInfo.getUserId());
        user.setBorrowCount(user.getBorrowCount() - 1);
        userMapper.updateUser(user);
    }

}
