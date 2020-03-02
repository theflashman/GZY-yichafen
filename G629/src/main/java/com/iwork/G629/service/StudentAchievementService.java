package com.iwork.G629.service;

import java.io.File;
import java.util.List;

import com.iwork.G629.entity.ItemInfo;
import com.iwork.G629.entity.StudentAchievement;
import com.iwork.G629.entity.UserInfo;

public interface StudentAchievementService {
	
	/**
	 * 更新itemName
	 * @param item
	 * @return
	 */
	int updateItem(ItemInfo item);
	
	/**
	 * 通过学生id获取学生成绩信息
	 * 
	 * @param stuId
	 * @return
	 */
	List<StudentAchievement> getStudentAchievementBystuId(Integer stuId, String stuName, String userName);

	/**
	 * 通过file传入excel，实现批量导入学生成绩信息
	 * 
	 * @param file
	 * @return
	 */
	int batchInsertStudentAchievementByExcel(File file, String extString, int itemId);

	/**
	 * 插入Excel表的信息
	 * 
	 * @param itemInfo
	 * @return
	 */
	int insertExcelFormInfo(ItemInfo itemInfo);

	/**
	 * 通过用户名和查询名来确定查询id
	 * 
	 * @param itemName
	 * @param userName
	 * @return
	 */
	Integer queryExcelFormItemId(String itemName, String userName);

	/**
	 * 获取某个用户的全部信息
	 * 
	 * @param userName
	 * @return
	 */
	UserInfo querySingleUserInfo(String userName);

	/**
	 * 返回用户名下所有查询的全部信息
	 * 
	 * @param userName
	 * @return
	 */
	List<ItemInfo> queryAllItemInfoByUserName(String userName);

	/**
	 * 批量修改查询下的学生信息
	 * 
	 * @param studentAchievementList
	 * @return
	 */
	int batchUpdateStudentAchievement(List<StudentAchievement> studentAchievementList);

	/**
	 * 通过身份验证和查询id来显示信息
	 * 
	 * @param itemId
	 * @param status
	 * @return
	 */
	List<StudentAchievement> queryAllstudentAchievementByItemId(int itemId, int status);

	/**
	 * 通过查询的唯一id来删除查询
	 * 
	 * @param itemId
	 * @return
	 */
	int deleteStudentAchievementByItemId(int itemId);

	/**
	 * 删除查询
	 * 
	 * @param itemId
	 * @return
	 */
	int deleteItem(int itemId);

	/**
	 * 通过查询名模糊查询
	 * 
	 * @param itemName
	 * @return
	 */
	public List<ItemInfo> queryItemByItemName(String itemName, String userName);

	/**
	 * 通过itemId和stuId来查找某一个学生的成绩
	 * 
	 * @param stuId
	 * @param itemId
	 * @return
	 */
	public List<StudentAchievement> queryByStuIdAndName(String stuId, String itemId);

	/**
	 * 输入itemid来导出为excel表
	 * 
	 * @param itemId
	 * @return
	 */
	public String exportToExcel(String itemId);

	/**
	 * 用戶想怎麽查就怎麽查
	 * @param studentAchievement
	 * @return
	 */
	public List<StudentAchievement> queryByUser(StudentAchievement studentAchievement);
	
	/**
	 * 最新款超级excel。
	 * @param file
	 * @param extString
	 * @param itemId
	 * @return
	 */
	public int superExcel(File file, String extString, int itemId);
	
	/**
	 * 更新查询次数
	 * @param itemId
	 * @return
	 */
	public int addQueryTimes(String itemId);
}
