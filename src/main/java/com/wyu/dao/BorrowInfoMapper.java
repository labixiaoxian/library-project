package com.wyu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wyu.entity.BorrowInfo;

/**
 *
 * @author 李达成
 * @since 2020/11/17
 *
 */
public interface BorrowInfoMapper {
	/**
	 * 
	 * @param id
	 */
	@Update("update lib_borrow_info set borrow_state = 1,examine_state=1 where id = #{id}")
	public void approve(Integer id);

	/**
	 * @apiNote 根据借阅ID删除借阅信息
	 * @param 借阅id
	 */
	@Delete("delete from lib_borrow_info where id = #{id}")
	public void deleteById(Integer id);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	@Select("select * from lib_borrow_info where user_id = #{userId} and borrow_state in (0,1)")
	public List<BorrowInfo> getBookShelf(Integer userId);

	/**
	 * 
	 * @param bookId
	 * @return
	 */
	@Select("select * from lib_borrow_info where book_id = #{bookId}")
	public List<BorrowInfo> getBorrowInfosByBookId(Integer bookId);

	@Select("select count(1) from lib_borrow_info")
	public int getBorrowInfosCount();

	/**
	 * 
	 * @param current
	 * @param pageSize
	 * @return
	 */
	@Select("select * from lib_borrow_info limit #{current},#{pageSize}")
	public List<BorrowInfo> getBorrowInfosPagination(Integer current, Integer pageSize);

	/**
	 * @apiNote 通过图书ID查询借阅信息
	 * @param bookId
	 * @return 有关该图书的所有借阅信息
	 */
	@Select("select * from lib_borrow_info where book_id = #{bookId} where borrow_state>=0")
	public List<BorrowInfo> getByBookId(Integer bookId);

	/**
	 * @apiNote 通过图书ID分页查询借阅信息
	 * @param bookId
	 * @param current
	 * @param size
	 * @return
	 */
	@Select("select * from lib_borrow_info where book_id = #{bookId} limit #{current},#{size}")
	public List<BorrowInfo> getByBookIdPagination(Integer bookId, int current, int size);

	/**
	 * @apiNote 通过借阅状态查询借阅信息
	 * @param state(0为审核中，1为借阅中，2为借阅完毕，已归还)
	 * @return 借阅状态对应的所有借阅信息
	 */
	@Select("select * from lib_borrow_info where borrow_state = #{state}")
	public List<BorrowInfo> getByBorrowState(Integer state);

	@Select("select count(1) from lib_borrow_info where borrow_state = #{state}")
	public int getByBorrowStateCount(Integer state);

	/**
	 * @apiNote 通过借阅状态分页查询借阅信息
	 * @param state
	 * @param current
	 * @param size
	 * @return
	 */
	@Select("select * from lib_borrow_info where borrow_state = #{state} limit #{current},#{size}")
	public List<BorrowInfo> getByBorrowStatePagination(Integer state, int current, int size);

	/**
	 * @apiNote 通过审核状态查询借阅信息
	 * @param state(0为未审核，1为审核通过)
	 * @return 审核状态对应的所有借阅信息
	 */
	@Select("select * from lib_borrow_info where examine_state = #{state}")
	public List<BorrowInfo> getByExamineState(Integer state);

	/**
	 * @apiNote 通过审核状态分页查询借阅信息
	 * @param state
	 * @param current
	 * @param size
	 * @return
	 */
	@Select("select * from lib_borrow_info where examine_state = #{state} limit #{current},#{size} order by borrow_date")
	public List<BorrowInfo> getByExamineStatePagination(Integer state, int current, int size);

	/**
	 * @apiNote 通过借阅ID查询借阅信息
	 * @param id
	 * @return 借阅id对应的借阅信息
	 */
	@Select("select * from lib_borrow_info where id = #{id}")
	public BorrowInfo getById(Integer id);

	/**
	 * @apiNote 通过用户ID查询借阅信息
	 * @param userId
	 * @return 有关该用户的所有借阅信息
	 */
	@Select("select * from lib_borrow_info where user_id = #{userId}")
	public List<BorrowInfo> getByUserId(Integer userId);

	/**
	 * 
	 * @param bookId
	 * @return
	 */
	@Select("select count(1) from lib_borrow_info where book_id = #{bookId} and borrow_state!=2 and borrow_state!=-1")
	public int getByBookIdAndNotReturnCount(Integer bookId);

	/**
	 * @apiNote 根据用户ID和借阅状态查询借阅信息
	 * @param userId
	 * @param status
	 * @return
	 */
	@Select("select * from lib_borrow_info where user_id = #{userId} and borrow_state = #{states}")
	public List<BorrowInfo> getByUserIdAndBorrowStates(Integer userId, Integer states);

	@Select("select count(1) from lib_borrow_info where user_id = #{userId}")
	public int getByUserIdCount(Integer userId);

	/**
	 * 
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @return
	 */
	@Select("select * from lib_borrow_info where user_id = #{userId} limit #{current},#{pageSize}")
	public List<BorrowInfo> getByUserIdPagination(Integer userId, int current, int pageSize);

	@Select("select count(1) from lib_borrow_info where user_id in (select user_id from lib_user_info where nickname like #{name})"
			+ "and borrow_state = #{states}")
	public int getByUserNameAndStatesCount(String name, Integer states);

	/**
	 * 
	 * @param name
	 * @param states
	 * @param current
	 * @param pageSize
	 * @return
	 */
	@Select("select * from lib_borrow_info where lib_borrow_info.user_id in (select lib_user_info.user_id from lib_user_info where nickname like #{name})"
			+ "and borrow_state = #{states} order by id limit #{current},#{pageSize}")
	public List<BorrowInfo> getByNickNameAndStatesPagination(String name, Integer states, Integer current,
			Integer pageSize);

	/**
	 * @apiNote 查询当天逾期借阅信息
	 * @return 当天逾期的借阅信息
	 */
	@Select("select * from lib_borrow_info where to_days(now())-to_days(return_date)=1 and borrow_state=1;")
	public List<BorrowInfo> getOverDueInfo();

	@Select("select count(1) from lib_borrow_info where to_days(now())-to_days(return_date)>=1 and borrow_state=1;")
	public int getOverDueCount();

	/**
	 * @apiNote 分页查询逾期借阅信息
	 * @param current
	 * @param size
	 * @return
	 */
	@Select("select * from lib_borrow_info where to_days(now())-to_days(return_date)>=1 limit #{current},#{size}")
	public List<BorrowInfo> getOverDueInfoPagination(int current, int size);

	/**
	 * @apiNote 插入借阅信息。注：可不用填借阅id，审核状态，借阅状态和续借状态
	 * @param BorrowInfo对象
	 */
	@Insert("insert into lib_borrow_info values(null,#{info.userId},#{info.bookId},#{info.borrowDate},#{info.returnDate},0,0,0)")
	public void insert(@Param("info") BorrowInfo info);

	/**
	 * @apiNote 查询所有借阅信息
	 * @return 所有借阅信息
	 */
	@Select("select * from lib_borrow_info")
	public List<BorrowInfo> list();

	/**
	 * @apiNote 更新借阅信息。
	 * @param BorrowInfo对象
	 */
	@Update("update lib_borrow_info set user_id=#{info.userId},book_id=#{info.bookId},examine_state=#{info.examineState},"
			+ "borrow_date = #{info.borrowDate},return_date = #{info.returnDate},"
			+ "borrow_state=#{info.borrowState},renew_state=#{info.renewState} where id = #{info.id}")
	public void update(@Param("info") BorrowInfo info);

	/**
	 * 
	 * @param id
	 * @param states
	 */
	@Update("update lib_borrow_info set borrow_state = #{states} where id = #{id}")
	public void updateStates(Integer id, Integer states);

	/**
	 * @apiNote 今日借书人数
	 * @return
	 */
	@Select("select count(1) from lib_borrow_info where to_days(now())=to_days(borrow_date) and borrow_state>0")
	public int numOfBorrowingToday();

	/**
	 * @apiNote 本月借阅人数
	 * @return
	 */
	@Select("SELECT count(1) FROM lib_borrow_info WHERE DATE_FORMAT( borrow_date, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' ) and borrow_state>0;")
	public int numOfBorrowingThisMonth();

	/**
	 * @apiNote 今年借阅人数
	 * @return
	 */
	@Select("SELECT count(1) FROM lib_borrow_info WHERE DATE_FORMAT( borrow_date, '%Y' ) = DATE_FORMAT( CURDATE( ) , '%Y' ) and borrow_state>0;")
	public int numOfBorrowingThisYear();

	@Select("select count(1) from lib_user_info where book_id in (select id from lib_book where book_name like #{bookName}) "
			+ "and user_id = #{userId}")
	public int getByBookNameAndUserIdCount(String bookName, Integer userId);

	@Select("select * from lib_user_info where book_id in (select id from lib_book where book_name like #{bookName}) "
			+ "and user_id = #{userId} limit #{current},#{pageSize}")
	public List<BorrowInfo> getByBookNameAndUserId(String bookName, Integer userId, Integer current, Integer pageSize);
}
