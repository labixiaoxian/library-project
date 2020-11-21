package com.wyu.service;

import java.util.List;

import com.wyu.entity.Punishment;
import com.wyu.entity.UserInfo;

/**
 * 
 * @author 李达成
 * @since 2020/11/18
 *
 */
public abstract class PunishmentService {
	public abstract List<Punishment> queryAll();

	public abstract List<Punishment> queryAllPagination(int current, int size);

	public abstract Punishment get(Integer id);

	public abstract List<Punishment> getByUserId(Integer userId);

	public abstract void deleteById(Integer id);

	public abstract List<Punishment> FuzzyqueryByNickName(String name, int current, int size);

	public abstract int listCount();

	public abstract int queryUserInfoCount(UserInfo userInfo);
}
