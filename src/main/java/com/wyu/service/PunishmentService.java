package com.wyu.service;

import java.util.List;

import com.wyu.entity.Punishment;

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

	public abstract void insert(Punishment punishment);

	public abstract void deleteById(Integer id);

	public abstract void update(Punishment punishment);
}
