package com.iwork.G629.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iwork.G629.entity.ItemInfo;
import com.iwork.G629.entity.StudentAchievement;
import com.iwork.G629.entity.UserInfo;

public interface StudentAchievementDao {

	/**
	 * 更新item
	 * 
	 * @param item
	 * @return
	 */
	int updateItem(@Param("item") ItemInfo item);

	/**
	 * 每次查询都调用这个功能，更新查询次数
	 * 
	 * @param itemId
	 * @return
	 */
	int addQueryTimes(String itemId);

	/**
	 * 根据学生id查询学生成绩信息
	 * 
	 * @param stuId
	 * @return
	 */
	List<StudentAchievement> queryStudentAchievementBystuId(@Param("stuId") Integer stuId,
			@Param("stuName") String stuName, @Param("userName") String userName);

	/**
	 * 通过excel上传学生成绩信息
	 * 
	 * @param file
	 * @return
	 */
	int batchInsertStudentAchievement(List<StudentAchievement> studentAchievementList);

	/**
	 * 创建表信息
	 * 
	 * @param itemInfo
	 * @return
	 */
	int insertExcelFormInfo(ItemInfo itemInfo);

	/**
	 * 通过用户名和查询名获取查询的唯一id号
	 * 
	 * @param userName
	 * @param itemName
	 * @return
	 */
	Integer queryItemId(@Param("itemName") String itemName, @Param("userName") String userName);

	/**
	 * 获取该用户名下用户的所有信息
	 * 
	 * @param userName
	 * @return
	 */
	UserInfo querySingleUserInfo(@Param("userName") String userName);

	/**
	 * 通过用户名返回该用户名下所有查询
	 * 
	 * @param userName
	 * @return
	 */
	List<ItemInfo> queryItemInfoList(@Param("userName") String userName);

	/**
	 * 
	 * 批量修改学生信息
	 * 
	 * @param itemId
	 * @return
	 */
	int batchUpdateStudentAchievement(List<StudentAchievement> studentAchievementList);

	/**
	 * 通过查询id号来获取该查询下所有学生的成绩信息，并根据老师来显示
	 * 
	 * @param itemId
	 * @param status
	 * @return
	 */
	List<StudentAchievement> queryAllstudentAchievementByItemId(@Param("itemId") int itemId);

	/**
	 * 通过查询的唯一id号来删除查询下所有内容
	 * 
	 * @param itemId
	 * @return
	 */
	int deleteStudentAchievementByItemId(@Param("itemId") int itemId);

	/**
	 * 删除查询
	 * 
	 * @param itemId
	 * @return
	 */
	int deleteItem(@Param("itemId") int itemId);

	/**
	 * 通过查询名模糊查询
	 * 
	 * @param itemName
	 * @return
	 */
	List<ItemInfo> queryItemByItemName(@Param("itemName") String itemName, @Param("userName") String userName);

	/**
	 * 学号和itemid来查询某一个学生的成绩
	 * 
	 * @return
	 */
	List<StudentAchievement> queryByStuIdAndName(@Param("itemId") String itemId, @Param("stuId") String stuId);

	/**
	 * 根据用户需要提供查询条件
	 * 
	 * @param studentAchievement
	 * @return
	 */
	List<StudentAchievement> queryByUser(@Param("sa") StudentAchievement studentAchievement);

}
