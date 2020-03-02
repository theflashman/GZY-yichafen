package com.iwork.G629.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwork.G629.BaseTest;
import com.iwork.G629.entity.ItemInfo;
import com.iwork.G629.entity.StudentAchievement;

public class StudentAchievementServiceTest extends BaseTest {

	@Autowired
	private StudentAchievementService studentAchievementService;

	@Test
	@Ignore
	public void testGetStudentAchievementBystuId() {
		Integer stuId = 1;
		String userName = "dsa";
		String stuName = "2";
		List<StudentAchievement> studentAchievement = studentAchievementService.getStudentAchievementBystuId(stuId,
				stuName, userName);
		System.out.println("学生姓名为：" + studentAchievement.get(0).getStuName());
	}

	@Test
	@Ignore
	public void testBatchInsertStudentAchievementByExcel() {
		File file = new File("C:/Users/曾越爸爸/Desktop/1111.xlsx");
		studentAchievementService.batchInsertStudentAchievementByExcel(file, ".xlsx", 9999);
	}

	@Test
	@Ignore
	public void testQuertItemId() {
		System.out.println(studentAchievementService.queryExcelFormItemId("第2次月考", "zy"));
	}

	@Test
	@Ignore
	public void testQuertSingleUserInfo() {
		System.out.println(studentAchievementService.querySingleUserInfo("zy").getUserName());
	}

	@Test
	@Ignore
	public void testQueryAllItemInfoByUserName() {
		System.out.println(studentAchievementService.queryAllItemInfoByUserName("zy").get(2).getItemId());
	}

	@Test
	@Ignore
	public void testQueryAllstudentAchievementByItemId() {
		System.out.println(studentAchievementService.queryAllstudentAchievementByItemId(7, 3).get(0).getAchievement());
	}

	@Test
	@Ignore
	public void testExportExcel() {

		System.out.println(studentAchievementService.exportToExcel("79"));
	}

	@Test
	@Ignore
	public void testquerybyUser() {
		StudentAchievement studentAchievement = new StudentAchievement();
		studentAchievement.setStuName("小兰");
		List<StudentAchievement> as = studentAchievementService.queryByUser(studentAchievement);
		System.out.println(as.get(2).getItemId());

	}

	@Test
	public void testUpdateItemName() {
		ItemInfo item = new ItemInfo();
		item.setItemId(4);
		item.setItemName("jdaskjdskadjaskldjasil");
		item.setLastEditTime(new Date());
		studentAchievementService.updateItem(item);
	}
}
