package com.wyu.service.serviceImpl.user;

import com.wyu.dao.UserInfoMapper;
import com.wyu.entity.UserInfo;
import com.wyu.service.user.UserInfoService;
import com.wyu.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * Created by XiaoXian on 2020/11/19.
 */
@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {


    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo findUserInfoByUserId(Integer userId) {
        if (!ObjectUtils.isEmpty(userId)){
            UserInfo userInfoByUserId = userInfoMapper.findUserInfoByUserId(userId);
            return userInfoByUserId;
        }
        return null;
    }

    @Override
    public UserInfo findUserInfoById(Integer id) {
        if (!ObjectUtils.isEmpty(id)){
            UserInfo userInfoById = userInfoMapper.findUserInfoById(id);
            return userInfoById;
        }
        return null;
    }

    @Override
    public List<UserInfo> findUserInfoList(UserInfo userInfoCondition, Integer currentPage, Integer pageSize) {
        if (ObjectUtils.isEmpty(currentPage) || ObjectUtils.isEmpty(pageSize)){
            return null;
        }
        int rowIndex = PageUtil.calculateRowIndex(currentPage, pageSize);
        List<UserInfo> userInfos = userInfoMapper.queryUserInfo(userInfoCondition, rowIndex, pageSize);
        return userInfos;
    }
}
