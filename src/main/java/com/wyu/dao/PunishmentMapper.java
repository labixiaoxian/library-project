package com.wyu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.wyu.entity.Punishment;

/**
 * 
 * @author 李达成
 * @since 2020/11/17
 *
 */
public interface PunishmentMapper {

//	@Results(id="punishMap",value = {
//			@Result(column = "id",property = "id",id = true,jdbcType = JdbcType.INTEGER),
//			@Result(column = "user_id",property = "userId",),
//			
//	})

	/**
	 * @apiNote 根据惩罚表ID获取对应数据
	 * @param id
	 * @return
	 */
	@Select("select * from lib_punishment where id = #{id}")
	public Punishment getById(Integer id);

	/**
	 * @apiNote 查询所有惩罚信息
	 * @return
	 */
	@Select("select * from lib_punishment")
	public List<Punishment> list();

	/**
	 * @apiNote 分页查询惩罚信息
	 * @param current
	 * @param size
	 * @return
	 */
	@Select("select * from lib_punishment limit #{current},#{size}")
	public List<Punishment> listPagination(int current, int size);

	/**
	 * @apiNote 根据用户ID查询有关该用户的惩罚情况
	 * @param userId
	 * @return
	 */
	@Select("select * from lib_punishment where user_id = #{userId}")
	public List<Punishment> getByUserId(Integer userId);

	/**
	 * @apiNote 新增一条惩罚记录
	 * @param punishment
	 */
	@Insert("insert into lib_punishment values(null,#{punishment.userId},#{punishment.fine},#{punishment.reason})")
	public void insert(Punishment punishment);

	@Delete("delete from lib_punishment where id = #{id}")
	public void deleteById(Integer id);
}