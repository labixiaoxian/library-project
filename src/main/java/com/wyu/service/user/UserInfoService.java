package com.wyu.service.user;

import com.wyu.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by XiaoXian on 2020/11/18.
 */
public interface UserInfoService {

   // public int modifyUserInfo()

    public UserInfo findUserInfoByUserId(Integer userId);

    public UserInfo findUserInfoById(Integer id);

    public List<UserInfo> findUserInfoList(UserInfo userInfoCondition,
                                           Integer currentPage,Integer pageSize);
}
