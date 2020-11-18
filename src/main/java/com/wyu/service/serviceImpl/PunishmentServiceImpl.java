package com.wyu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyu.dao.PunishmentMapper;
import com.wyu.dao.UserInfoMapper;
import com.wyu.entity.Punishment;
import com.wyu.service.PunishmentService;

/**
 * 
 * @author 李达成
 * @since 2020/11/18
 */
@Service
public class PunishmentServiceImpl extends PunishmentService {
	@Autowired
	PunishmentMapper punishmentMapper;

	@Autowired
	UserInfoMapper userInfoMapper;

	/**
	 * @apiNote 查询全部惩罚记录
	 * @return
	 */
	@Override
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
	public List<Punishment> queryAllPagination(int current, int size) {
		List<Punishment> list = punishmentMapper.listPagination(current, size);
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
	public List<Punishment> getByUserId(Integer userId) {
		// TODO Auto-generated method stub
		List<Punishment> list = punishmentMapper.getByUserId(userId);
		for (Punishment punishment : list) {
			punishment.setUserInfo(userInfoMapper.findUserInfoByUserId(punishment.getUserId()));
		}
		return list;
	}

	/**
	 * @apiNote 新增一条惩罚记录
	 * @param punishment
	 * @return
	 */
	@Override
	public void insert(Punishment punishment) {
		// TODO Auto-generated method stub
		punishmentMapper.insert(punishment);
	}

	/**
	 * @apiNote 删除一条惩罚记录
	 * @param id
	 * @return
	 */
	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		punishmentMapper.deleteById(id);
	}

	/**
	 * @apiNote 更新罚金
	 * @param punishment
	 * @return
	 */
	@Override
	public void update(Punishment punishment) {
		// TODO Auto-generated method stub
		punishmentMapper.updateFine(punishment);
	}

}
