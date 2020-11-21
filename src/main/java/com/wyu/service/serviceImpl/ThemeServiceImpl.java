package com.wyu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wyu.dao.ThemeMapper;
import com.wyu.entity.Theme;
import com.wyu.service.ThemeService;

/**
 *
 * @since 2020/11/19
 * @author Administrator
 *
 */
@Service
@CacheConfig(cacheNames = "theme")
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeMapper themeMapper;

    /**
     * 
     * @apiNote 通过id删除一条主题信息
     * @param id
     * 
     */
    @Override
    @CacheEvict(allEntries = true)
    public void deleteById(int id) {
        themeMapper.deleteById(id);
    }

    /**
     * 
     * @apiNote 新增一条主题信息
     * @param type
     * 
     */
    @Override
    @CacheEvict(allEntries = true)
    public void insertTheme(Theme theme) {
        themeMapper.newTheme(theme);
    }

    /**
     * 
     * @apiNote 查询全部主题信息
     * 
     */
    @Override
    @Cacheable(keyGenerator = "myGenerator", sync = true)
    public List<Theme> queryAll() {
        List<Theme> list = themeMapper.queryAll();
        return list;
    }

    /**
     * 
     * @apiNote 通过id查询主题
     * @param id
     * 
     */
    @Override
    @Cacheable(keyGenerator = "myGenerator", sync = true)
    public Theme queryById(int id) {
        Theme theme = themeMapper.queryById(id);
        return theme;
    }

    /**
     * 
     * @apiNote 更新一条主题信息
     * @param type
     * 
     */
    @Override
    @CacheEvict(allEntries = true)
    public void updateById(Theme theme) {
        themeMapper.update(theme);
    }

}
