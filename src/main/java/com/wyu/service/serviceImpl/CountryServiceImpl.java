package com.wyu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wyu.dao.CountryMapper;
import com.wyu.entity.Country;
import com.wyu.service.CountryService;

/**
 *
 * @since 2020/11/19
 * @author 李润东
 *
 */
@Service
@CacheConfig(cacheNames = "country")
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryMapper countryMapper;

    /**
     * 
     * @apiNote 通过id删除一条国家信息
     * @param id
     * 
     */
    @Override
    @CacheEvict(allEntries = true)
    public void deleteById(int id) {
        countryMapper.deleteById(id);
    }

    /**
     * 
     * @apiNote 新增一条国家信息
     * @param type
     * 
     */
    @Override
    @CacheEvict(allEntries = true)
    public void insert(Country country) {
        countryMapper.newCountry(country);
    }

    /**
     * 
     * @apiNote 查询全部国家信息
     * 
     */
    @Override
    @Cacheable(keyGenerator = "myGenerator", sync = true)
    public List<Country> queryAll() {
        List<Country> list = countryMapper.queryAll();
        return list;
    }

    /**
     * 
     * @apiNote 通过id查询国家
     * @param id
     * 
     */
    @Override
    @Cacheable(keyGenerator = "myGenerator", sync = true)
    public Country queryById(int id) {
        Country country = countryMapper.queryById(id);
        return country;
    }

    /**
     * 
     * @apiNote 更新一条国家信息
     * @param type
     * 
     */
    @Override
    @CacheEvict(allEntries = true)
    public void update(Country country) {
        countryMapper.update(country);
    }

}
