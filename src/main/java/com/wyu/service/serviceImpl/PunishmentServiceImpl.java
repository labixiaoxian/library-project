package com.wyu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.wyu.dao.PunishmentMapper;
import com.wyu.dao.UserInfoMapper;
import com.wyu.dao.UserMapper;
import com.wyu.entity.Punishment;
import com.wyu.entity.User;
import com.wyu.entity.UserInfo;
import com.wyu.service.PunishmentService;

/**
 *
 * @author 李达成
 * @since 2020/11/18
 */
@Service
@CacheConfig(cacheNames = "punishment")
public class PunishmentServiceImpl extends PunishmentService {
	@Autowired
	PunishmentMapper punishmentMapper;

	@Autowired
	UserInfoMapper userInfoMapper;

	@Autowired
	UserMapper userMapper;

	/**
	 * @apiNote 删除一条惩罚记录
	 * @param id
	 * @return
	 */
	@Override
	@CacheEvict(allEntries = true)
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		Punishment punishment = punishmentMapper.getById(id);
		punishmentMapper.deleteById(id);
		User user = userMapper.findUserById(punishment.getUserId());
		user.setCredit(user.getCredit() < 60 ? 60 : user.getCredit());
		userMapper.updateUser(user);
	}

	/**
	 * @apiNote 根据用户名模糊查询惩罚记录
	 * @param name currentPage size
	 */
	@Override
	@Cacheable(keyGenerator = "myGenerator")
	public List<Punishment> FuzzyqueryByNickName(String name, int currentPage, int pageSize) {
		List<Punishment> list = punishmentMapper.queryByNickName("%" + name + "%", (currentPage - 1) * pageSize,
				pageSize);
		for (Punishment punishment : list) {
			punishment.setUserInfo(userInfoMapper.findUserInfoByUserId(punishment.getUserId()));
		}
		return list;
	}

	/**
	 * @apiNote 根据惩罚ID查询惩罚记录
	 * @param id
	 * @return
	 */
	@Override
	@Cacheable(keyGenerator = "myGenerator")
	public Punishment get(Integer id) {
		// TODO Auto-generated method stub
		Punishment punishment = punishmentMapper.getById(id);
		try {
			punishment.setUserInfo(userInfoMapper.findUserInfoByUserId(punishment.getUserId()));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return punishment;
	}

	/**
	 * @apiNote 根据用户ID查询惩罚记录
	 * @param userId
	 * @return
	 */
	@Override
	@Cacheable(keyGenerator = "myGenerator")
	public List<Punishment> getByUserId(Integer userId) {
		// TODO Auto-generated method stub
		List<Punishment> list = punishmentMapper.getByUserId(userId);
		for (Punishment punishment : list) {
			punishment.setUserInfo(userInfoMapper.findUserInfoByUserId(punishment.getUserId()));
		}
		return list;
	}

	/**
	 * @apiNote 查询全部惩罚记录
	 * @return
	 */
	@Override
	@Cacheable(keyGenerator = "myGenerator")
	public List<Punishment> queryAll() {
		List<Punishment> list = punishmentMapper.list();
		for (Punishment punishment : list) {
			punishment.setUserInfo(userInfoMapper.findUserInfoById(punishment.getUserId()));
		}
		return list;
	}

	/**
	 * @apiNote 分页查询
	 * @param current
	 * @param size
	 * @return
	 */
	@Override
	@Cacheable(keyGenerator = "myGenerator")
	public List<Punishment> queryAllPagination(int current, int size) {
		List<Punishment> list = punishmentMapper.listPagination((current - 1) * size, size);
		for (Punishment punishment : list) {
			punishment.setUserInfo(userInfoMapper.findUserInfoByUserId(punishment.getUserId()));
		}
		return list;
	}

	@Override
	@Cacheable(keyGenerator = "myGenerator")
	public int listCount() {
		// TODO Auto-generated method stub
		return punishmentMapper.listCount();
	}

	@Override
	@Cacheable(keyGenerator = "myGenerator")
	public int queryUserInfoCount(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userInfoMapper.queryUserInfoCount(userInfo);
	}

	@Override
	@Cacheable(keyGenerator = "myGenerator")
	public int FuzzyqueryByNickNameCount(String name) {
		// TODO Auto-generated method stub
		return punishmentMapper.queryByNickNameCount("%" + name + "%");
	}

	@Override
	@Cacheable(keyGenerator = "myGenerator")
	public int getByUserIdCount(Integer userId) {
		// TODO Auto-generated method stub
		return punishmentMapper.getByUserIdCount(userId);
	}

}
