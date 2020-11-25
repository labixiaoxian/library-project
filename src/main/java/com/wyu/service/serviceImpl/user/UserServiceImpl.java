package com.wyu.service.serviceImpl.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.wyu.dao.BorrowInfoMapper;
import com.wyu.dao.PunishmentMapper;
import com.wyu.dao.UserInfoMapper;
import com.wyu.dao.UserMapper;
import com.wyu.dao.UserRoleMapper;
import com.wyu.entity.BorrowInfo;
import com.wyu.entity.Punishment;
import com.wyu.entity.User;
import com.wyu.entity.UserInfo;
import com.wyu.entity.UserRole;
import com.wyu.service.user.UserService;
import com.wyu.utils.DateFormateUtile;
import com.wyu.utils.SaltUtil;

/**
 * Created by XiaoXian on 2020/11/18.
 */
@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Resource
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private BorrowInfoMapper borrowInfoMapper;

	@Autowired
	private PunishmentMapper punishmentMapper;

	@Override
	public User findUserByUsername(String username) {
		if (!StringUtils.isEmpty(username)) {
			return userMapper.findUserByUsername(username);
		}
		return null;
	}

	@Override
	public User findRolesByUsername(String username) {
		if (!StringUtils.isEmpty(username)) {
			return userMapper.findRolesByUsername(username);
		}
		return null;
	}

	@Override
	public int modifyPassword(Integer userId, String oldPassword, String newPassword) {
		// 数据为空
		if (ObjectUtils.isEmpty(userId) || ObjectUtils.isEmpty(oldPassword) || ObjectUtils.isEmpty(newPassword)) {
			return 0;
		}
		User userById = userMapper.findUserById(userId);
		// 查询不到该用户
		if (ObjectUtils.isEmpty(userById)) {
			return -1;
		}
		// 该用户被注销
		if (userById.getStatus() == 0) {
			return -4;
		}

		// 新密码与旧密码相同
		if (oldPassword.equals(newPassword)) {
			return -2;
		}

		// 旧密码不正确
		Md5Hash old = new Md5Hash(oldPassword, userById.getPwdSalt(), 1024);
		if (!old.toHex().equals(userById.getPwdHash())) {
			return -3;
		}

		// 对用户的密码进行加密
		String salt = SaltUtil.getSalt(8);
		Md5Hash md5Hash = new Md5Hash(newPassword, salt, 1024);
		// 对用户的密码和盐进行更改
		User user = new User();
		user.setId(userId);
		user.setPwdHash(md5Hash.toHex());
		user.setPwdSalt(salt);
		return userMapper.updateUser(user);

	}

	@Override
	public int register(String userName, String nickname, String email, String password, String twicePassword) {

		Map map = new HashMap();

		// 数据为空
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(email) || StringUtils.isEmpty(password)
				|| StringUtils.isEmpty(twicePassword) || StringUtils.isEmpty(nickname)) {
			return 0;
		}
		// 防止用户名重复
		User userByUsername = userMapper.findUserByUsername(userName);
		if (!ObjectUtils.isEmpty(userByUsername)) {
			return -1;
		}

		// 防止输入的两次密码不相同
		if (!password.equals(twicePassword)) {
			return -2;
		}
		// 注册
		String salt = SaltUtil.getSalt(8);
		Md5Hash md5Hash = new Md5Hash(password, salt, 1024);
		// user
		User user = new User();
		user.setBorrowCount(0);
		user.setCredit(100);
		user.setPwdSalt(salt);
		user.setPwdHash(md5Hash.toHex());
		user.setStatus(0);
		user.setUsername(userName);
		user.setRegisterTime(new Date());
		// userinfo
		UserInfo userInfo = new UserInfo();
		userInfo.setUser(user);
		userInfo.setEmail(email);
		userInfo.setBirthday(DateFormateUtile.stampToString("1997-12-2"));
		userInfo.setNickname(nickname);
		userInfo.setSex(1);
		userInfo.setAge(18);
		userInfo.setAddress("");
		userInfo.setPersonalDesc("");
		userInfo.setPicture("");
		userInfo.setTelephone("");

		// 写入数据库
		userMapper.insertUser(user);
		userInfoMapper.insertUserInfo(userInfo);
		// 发送激活邮箱
		User registerUser = userMapper.findUserByUsername(userName);

		// userrole
		UserRole userRole = new UserRole();
		userRole.setRoleId(2);
		userRole.setUserId(registerUser.getId());
		userRoleMapper.insertUserRole(userRole);

		map.put("userId", registerUser.getId());
		map.put("email", email);
		rabbitTemplate.convertAndSend("directExchange", "xian", map);
		return 200;
	}

	@Override
	public int activeUser(Integer userId) {
		if (ObjectUtils.isEmpty(userId)) {
			return 0;
		}
		User user = new User();
		user.setId(userId);
		user.setStatus(1);
		return userMapper.updateUser(user);
	}

	@Override
	public int cancellation(Integer userId) {
		if (!ObjectUtils.isEmpty(userId)) {// 判断参数是否为空
			User user = userMapper.findUserById(userId);
//            int flag = 0;//标志位
			if (ObjectUtils.isEmpty(user)) {
				return -2;
			}
			if (user.getStatus() == 1) {// 判断用户状态
				// 检查是否有借书的状态为0或1的情况
				List<BorrowInfo> borrowList = borrowInfoMapper.getByUserId(userId);
				for (int i = 0; i < borrowList.size(); i++) {
					if (borrowList.get(i).getBorrowState() == 1 || borrowList.get(i).getBorrowState() == 0) {
						return -1;
					}
				}
				// 注销用户
				// 1.删除惩罚信息
				List<Punishment> punishmentList = punishmentMapper.getByUserId(userId);
				for (int i = 0; i < punishmentList.size(); i++) {
					punishmentMapper.deleteById(punishmentList.get(i).getId());
				}
				// 2.删除借书记录
				for (int i = 0; i < borrowList.size(); i++) {
					borrowInfoMapper.deleteById(borrowList.get(i).getId());
				}
				// 3.修改用户状态
				user.setStatus(0);
				userMapper.updateUser(user);
				return 1;
			}
		}
		return 0;
	}

}
