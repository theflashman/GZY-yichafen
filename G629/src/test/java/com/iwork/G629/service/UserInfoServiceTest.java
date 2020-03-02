package com.iwork.G629.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iwork.G629.BaseTest;
import com.iwork.G629.entity.UserInfo;

@Service
public class UserInfoServiceTest extends BaseTest {
	@Autowired
	private UserInfoService userInfoService;

	@Test
	@Ignore
	public void testGetUserInfoList() {
		UserInfo userInfo = userInfoService.getUserInfoList("dsa", "123");
		assertEquals("dsa", userInfo.getUserName());
	}

	@Test
	@Ignore
	public void testInsertUserInfo() {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName("testuser");
		userInfo.setPassword("123456");
		userInfo.setCreateTime(new Date());
		userInfo.setStatus(1);
		int a = userInfoService.insertUserInfo(userInfo);
		assertEquals(1, a);
	}

	@Test
	@Ignore
	public void testUpdateUserInfo() {
		int effectedNum = userInfoService.updateUserInfo("dsa", "1234567890", "123456");
		assertEquals(1, effectedNum);
	}
	
	@Test
	public void testQuerySingleUserInfo() {
		//UserInfo userInfo = userInfoService.querySingleUserInfo("zy");
		System.out.println(userInfoService.querySingleUserInfo("zy").getUserName());
	}
}
