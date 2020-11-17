package com.wyu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wyu.entity.Notice;

/**
 * 
 * @author 李达成
 * @since 2020/11/17
 * 
 */
public interface NoticeDao {
	/**
	 * @apiNote 查询所有公告
	 * @return 所有公告
	 */
	@Select("select * from lib_notice order by release_time desc")
	public List<Notice> list();

	/**
	 * @apiNote 分页查询所有公告
	 * @param currentPage
	 * @param size
	 * @return
	 */
	@Select("select * from lib_notice order by release_time desc limit #{currentPage},#{size}")
	public List<Notice> listPagination(int currentPage, int size);

	/**
	 * @apiNote 根据id查询公告
	 * @param id
	 * @return id对应的公告
	 */
	@Select("select * from lib_notice where id = #{id}")
	public Notice getById(Integer id);

	/**
	 * @apiNote 获取最新公告
	 * @return
	 */
	@Select("select * from lib_notice order by release_time desc limit 0,1")
	public Notice getLatestNotice();

	/**
	 * @apiNote 插入一条公告
	 * @param notice
	 */
	@Insert("insert into lib_notice values(null,#{notice.noticeName},#{notice.content},null)")
	public void insert(@Param("notice") Notice notice);

	/**
	 * @apiNote 根据id删除一条公告
	 * @param id
	 */
	@Delete("delete from lib_notice where id = #{id}")
	public void deleteById(Integer id);

	@Update("update lib_notice set notice_name=#{notice.noticeName},content=#{notice.content} where id = #{notice.id}")
	public void update(@Param("notice") Notice notice);
}
