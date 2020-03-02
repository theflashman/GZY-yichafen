package com.iwork.G629.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwork.G629.BaseTest;
import com.iwork.G629.entity.UserInfo;

public class UserInfoDaoTest extends BaseTest {
	@Autowired
	private UserInfoDao userInfoDao;

	@Test
	@Ignore
	public void testQueryUserInfo() {
		UserInfo userInfo = userInfoDao.queryUserInfo("dsa", "123456789");
		System.out.println("显示：" + userInfo.getUserName());
	}

	@Test
	@Ignore
	public void testInsertUserInfo() {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName("testuser2");
		userInfo.setPassword("123456");
		userInfo.setCreateTime(new Date());
		userInfo.setStatus(1);
		userInfo.setEmail("857141453@qq.com");
		userInfo.setPersonalName("曾越");
		userInfo.setQq(new Integer(857141453));
		userInfo.setUnit("广州中医药大学医学信息工程学院医学信息工程");
		int a = userInfoDao.insertUserInfo(userInfo);
		assertEquals(1, a);
	}

	@Test
	@Ignore
	public void testUpdateUserInfo() {
		int effectedNum = userInfoDao.updateUserInfo("dsa", "123", "1234567890");
		assertEquals(1, effectedNum);
	}
	
	@Test
	@Ignore
	public void testQuertSingleUserInfo() {
		UserInfo userInfo = userInfoDao.querySingleUserInfo("zy");
		System.out.println(userInfo.getUserId().toString());
	}
	
	@Test
	public void testUpdateUserAllInfo() {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName("testuser2");
		userInfo.setPassword("123456");
		userInfo.setCreateTime(new Date());
		userInfo.setStatus(1);
		userInfo.setEmail("857141453999@qq.com");
		userInfo.setPersonalName("曾越daren");
		userInfo.setQq(new Integer(857141453));
		userInfo.setUnit("广州中医药大学医学信息工程学院医学信息工程");
		int a = userInfoDao.updateUserAllInfo(userInfo);
		assertEquals(1, a);
	}
}
