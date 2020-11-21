package com.wyu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wyu.dao.NoticeMapper;
import com.wyu.entity.Notice;
import com.wyu.service.NoticeService;

/**
 *
 * @author 李达成
 * @since 2020/11/17
 */
@Service
@CacheConfig(cacheNames = "notice")
public class NoticeServiceImpl extends NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * @apiNote 根据ID删除一条公告
     * @param id
     */
    @Override
    @CacheEvict(allEntries = true)
    public void delete(Integer id) {
        noticeMapper.deleteById(id);
    }

    /**
     * @apiNote 模糊查询
     * @param name
     * @return
     */
    @Override
    @Cacheable(keyGenerator = "myGenerator", sync = true)
    public List<Notice> fuzzyQuery(String name) {
        name = "%" + name + "%";
        return noticeMapper.fuzzyQueryByname(name);
    }

    @Override
    @Cacheable(keyGenerator = "myGenerator", sync = true)
    public int fuzzyQueryBynameCount(String name) {
        // TODO Auto-generated method stub
        return noticeMapper.fuzzyQueryBynameCount(name);
    }

    /**
     * @apiNote 分页模糊查询
     * @param name
     * @param currentPage
     * @param size
     * @return
     */
    @Override
    @Cacheable(keyGenerator = "myGenerator", sync = true)
    public List<Notice> fuzzyQueryPagination(String name, int currentPage, int size) {
        name = "%" + name + "%";
        return noticeMapper.fuzzyQueryBynamePagination(name, (currentPage - 1) * size, size);
    }

    /**
     * @apiNote 根据ID查公告
     * @param id
     * @return
     */
    @Override
    @Cacheable(keyGenerator = "myGenerator", sync = true)
    public Notice get(Integer id) {
        return noticeMapper.getById(id);
    }

    /**
     * @apiNote 获取最新公告
     * @return
     */
    @Override
    @Cacheable(keyGenerator = "myGenerator", sync = true)
    public Notice getLatestNotice() {
        return noticeMapper.getLatestNotice();
    }

    /**
     * @apiNote 增加一条公告
     * @param name
     * @param content
     */
    @Override
    @CacheEvict(allEntries = true)
    public void insert(Notice notice) {
        noticeMapper.insert(notice);
    }

    @Override
    @Cacheable(keyGenerator = "myGenerator", sync = true)
    public int listCount() {
        // TODO Auto-generated method stub
        return noticeMapper.listCount();
    }

    /**
     * @apiNote 查询全部公告
     * @return
     */
    @Override
    @Cacheable(keyGenerator = "myGenerator", sync = true)
    public List<Notice> queryAll() {
        return noticeMapper.list();
    }

    /**
     * @apiNote 分页查询所有公告
     * @param currentPage
     * @param size
     * @return
     */
    @Override
    @Cacheable(keyGenerator = "myGenerator", sync = true)
    public List<Notice> queryAllPagination(int currentPage, int size) {
        return noticeMapper.listPagination((currentPage - 1) * size, size);
    }

    /**
     * @apiNote 更新一条公告
     * @param notice
     */
    @Override
    @CacheEvict(allEntries = true)
    public Notice update(Notice notice) {
        noticeMapper.update(notice);
        return notice;
    }
}
