package com.wyu.dao;

import com.wyu.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by XiaoXian on 2020/11/18.
 */
public interface UserInfoMapper {

    /**
     * 通过userId查询用户详细信息
     * @param userId
     * @return
     */
    UserInfo findUserInfoByUserId(Integer userId);

    /**
     * 通过Id查询用户详细信息
     * @param id
     * @return
     */
    UserInfo findUserInfoById(Integer id);

    /**
     * 添加用户详细信息
     * @param userInfo
     * @return
     */
    int insertUserInfo(UserInfo userInfo);

    /**
     * 修改用户详细信息
     * @param userInfo
     * @return
     */
    int updateUserInfo(UserInfo userInfo);


    /**
     * 通过用户详情Id删除用户详情信息
     * @param userInfoId
     * @return
     */
    int deleteUserInfoById(Integer userInfoId);


    /**
     * 条件查询用户查询信息
     * @param userInfoCondition
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<UserInfo> queryUserInfo(@Param("userInfoCondition")UserInfo userInfoCondition,
                                 @Param("rowIndex")Integer rowIndex,@Param("pageSize")Integer pageSize);

    /**
     * 条件查询用户查询信息的数量
     * @param userInfoCondition
     * @return
     */
    int queryUserInfoCount(@Param("userInfoCondition")UserInfo userInfoCondition);

}
