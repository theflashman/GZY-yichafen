package com.iwork.G629.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwork.G629.BaseTest;
import com.iwork.G629.entity.ItemInfo;
import com.iwork.G629.entity.StudentAchievement;
import com.iwork.G629.entity.UserInfo;

public class StudentAchievementDaoTest extends BaseTest {
	@Autowired
	private StudentAchievementDao studentAchievementDao;

	@Test
	@Ignore
	public void testQueryStudentAchievementBystuId() {
		Integer stuId = 56;
		String userName = "dsa";
		String stuName = "dads";
		List<StudentAchievement> studentAchievement = studentAchievementDao.queryStudentAchievementBystuId(stuId,
				stuName, userName);
		System.out.println("学生名字为：" + studentAchievement.get(0).getStuName());
	}

	@Test
	@Ignore
	public void testInsertStudentAchievement() {
		List<StudentAchievement> studentAchievementList = new ArrayList<StudentAchievement>();
		StudentAchievement studentAchievement = new StudentAchievement();
		studentAchievement.setStuName("测试者1");
		studentAchievement.setStuGrade(0);
		studentAchievement.setStuClass(0);
		studentAchievement.setAchievement(00);
		studentAchievement.setItemId(9999);
		StudentAchievement studentAchievement2 = new StudentAchievement();
		studentAchievement2.setStuName("测试者2");
		studentAchievement2.setStuGrade(0);
		studentAchievement2.setStuClass(0);
		studentAchievement2.setItemId(9999);
		studentAchievementList.add(studentAchievement);
		studentAchievementList.add(studentAchievement2);
		int effectedNum = studentAchievementDao.batchInsertStudentAchievement(studentAchievementList);
		assertEquals(2, effectedNum);
	}

	@Test
	@Ignore
	public void testInsertExcelFormInfo() {
		ItemInfo itemInfo = new ItemInfo();
		itemInfo.setItemName("第2次月考");
		itemInfo.setUserName("zy");
		int effectedNum = studentAchievementDao.insertExcelFormInfo(itemInfo);
		assertEquals(1, effectedNum);
	}

	@Test
	@Ignore
	public void testQueryItemId() {
		int effectedNum = studentAchievementDao.queryItemId("第2次月考", "zy");
		assertEquals(4, effectedNum);
	}

	@Test
	@Ignore
	public void testQuerySingleUserInfo() {
		UserInfo userInfo = studentAchievementDao.querySingleUserInfo("zy");
		System.out.println(userInfo.getUserName());
	}

	@Test
	public void testQueryItemInfoList() {
		List<ItemInfo> itemInfoList = studentAchievementDao.queryItemInfoList("root");
		System.out.println(itemInfoList.get(0).getItemName() + ":" + itemInfoList.get(0).getQueryTimes());
	}

	@Test
	@Ignore
	public void testBatchUpdateStudentAchievement() {
		List<StudentAchievement> studentAchievementList = new ArrayList<>();
		StudentAchievement studentAchievement1 = new StudentAchievement();
		StudentAchievement studentAchievement2 = new StudentAchievement();
		studentAchievement1.setAchievement(100);
		studentAchievement1.setItemId(32);
		studentAchievement1.setStuName("2");
		studentAchievement1.setStuId(298);
		studentAchievement1.setLeaveWord("liuyan2");
		studentAchievement2.setAchievement(85);
		studentAchievement2.setItemId(32);
		studentAchievement2.setStuName("3");
		studentAchievement2.setStuId(299);
		studentAchievement2.setLeaveWord("liuyan");
		studentAchievementList.add(studentAchievement1);
		studentAchievementList.add(studentAchievement2);
		studentAchievementDao.batchUpdateStudentAchievement(studentAchievementList);
	}

	@Test
	@Ignore
	public void testQueryAllstudentAchievementByItemId() {
		List<StudentAchievement> studentAchievementList = new ArrayList<>();
		studentAchievementList = studentAchievementDao.queryAllstudentAchievementByItemId(7);
		System.out.println(studentAchievementList.get(1).getAchievement());
	}

	@Test
	@Ignore
	public void testDeleteStudentAchievementByItemId() {
		int effectedNum = studentAchievementDao.deleteStudentAchievementByItemId(5);
		assertEquals(11, effectedNum);
	}

	@Test
	@Ignore
	public void testDeleteItem() {
		int effectedNum = studentAchievementDao.deleteItem(5);
		assertEquals(1, effectedNum);
	}

	@Test
	@Ignore
	public void testQueryItemByItemName() {
		List<ItemInfo> effectedNum = studentAchievementDao.queryItemByItemName("月考", "56");
		System.out.println(effectedNum.get(1).getItemName());
	}

	@Test
	@Ignore
	public void testQueryByUser() {
		StudentAchievement studentAchievement = new StudentAchievement();
		studentAchievement.setItemId(79);
		System.out.println(studentAchievementDao.queryByUser(studentAchievement).get(0).getStuName());
	}

	@Test
	@Ignore
	public void testAddqueryTimes() {
		studentAchievementDao.addQueryTimes("6");
	}
}
