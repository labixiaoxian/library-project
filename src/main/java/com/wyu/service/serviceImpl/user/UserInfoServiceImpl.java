package com.wyu.service.serviceImpl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.wyu.dao.UserInfoMapper;
import com.wyu.dao.UserMapper;
import com.wyu.entity.User;
import com.wyu.entity.UserInfo;
import com.wyu.service.user.UserInfoService;
import com.wyu.utils.PageUtil;

/**
 * Created by XiaoXian on 2020/11/19.
 */
@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserInfo findUserInfoByUserId(Integer userId) {
		if (!ObjectUtils.isEmpty(userId)) {
			UserInfo userInfoByUserId = userInfoMapper.findUserInfoByUserId(userId);
			return userInfoByUserId;
		}
		return null;
	}

	@Override
	public UserInfo findUserInfoById(Integer id) {
		if (!ObjectUtils.isEmpty(id)) {
			UserInfo userInfoById = userInfoMapper.findUserInfoById(id);
			return userInfoById;
		}
		return null;
	}

	@Override
	public List<UserInfo> findUserInfoList(UserInfo userInfoCondition, Integer currentPage, Integer pageSize) {
		if (ObjectUtils.isEmpty(currentPage) || ObjectUtils.isEmpty(pageSize)) {
			return null;
		}
		int rowIndex = PageUtil.calculateRowIndex(currentPage, pageSize);
		List<UserInfo> userInfos = userInfoMapper.queryUserInfo(userInfoCondition, rowIndex, pageSize);
		return userInfos;
	}

	@Override
	public int updateUserInfo(UserInfo userInfo) {
		if (!ObjectUtils.isEmpty(userInfo)) {
			UserInfo userInfo2 = userInfoMapper.findUserInfoById(userInfo.getId());
			User user = userMapper.findUserById(userInfo2.getUser().getId());
			if (user.getStatus() == 0) {
				return -1;
			}
			int result = userInfoMapper.updateUserInfo(userInfo);
			return result;
		}
		return 0;
	}

	@Override
	public int queryUserInfoCount(UserInfo userInfoCondition) {
		return userInfoMapper.queryUserInfoCount(userInfoCondition);
	}
}
