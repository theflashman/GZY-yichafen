package com.iwork.G629.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iwork.G629.dao.UserInfoDao;
import com.iwork.G629.entity.UserInfo;
import com.iwork.G629.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoDao userInfoDao;

	@Override
	public UserInfo getUserInfoList(String userName, String password) {
		// TODO 自动生成的方法存根
		return userInfoDao.queryUserInfo(userName, password);
	}

	@Override
	public int insertUserInfo(UserInfo userInfo) throws RuntimeException {
		try {
			userInfoDao.insertUserInfo(userInfo);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	@Override
	public int updateUserInfo(String userName, String oldPassword, String newPassword) {
		int effectedNum = userInfoDao.updateUserInfo(userName, oldPassword, newPassword);
		return effectedNum;
	}

	@Override
	public UserInfo querySingleUserInfo(String userName) {
		return userInfoDao.querySingleUserInfo(userName);

	}

	@Override
	public int updateUserAllInfo(UserInfo userInfo) {
		int effectedNum = userInfoDao.updateUserAllInfo(userInfo);
		return effectedNum;
	}

}
