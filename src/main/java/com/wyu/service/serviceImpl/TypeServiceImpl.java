package com.wyu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wyu.dao.TypeMapper;
import com.wyu.entity.Type;
import com.wyu.service.TypeService;

/**
 *
 * @author 李润东
 * @since 2020/11/19
 *
 */
@Service
@CacheConfig(cacheNames = "type")
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    /**
     * 
     * @apiNote 通过id删除一条类型信息
     * @param id
     * 
     */
    @Override
    @CacheEvict(allEntries = true)
    public void deleteById(int id) {
        typeMapper.deleteById(id);
    }

    /**
     * 
     * @apiNote 新增一条类型信息
     * @param type
     * 
     */
    @Override
    @CacheEvict(allEntries = true)
    public void insertType(Type type) {
        typeMapper.newType(type);
    }

    /**
     * 
     * @apiNote 查询全部类型信息
     * 
     */
    @Override
    @Cacheable(keyGenerator = "myGenerator", sync = true)
    public List<Type> queryAll() {
        List<Type> list = typeMapper.queryAll();
        return list;
    }

    /**
     * 
     * @apiNote 通过id查询类型
     * @param id
     * 
     */
    @Override
    @Cacheable(keyGenerator = "myGenerator", sync = true)
    public Type queryById(int id) {
        Type type = typeMapper.queryById(id);
        return type;
    }

    /**
     * 
     * @apiNote 更新一条类型信息
     * @param type
     * 
     */
    @Override
    @CacheEvict(allEntries = true)
    public void updateById(Type type) {
        typeMapper.updateById(type);
    }

}
