package com.iwork.G629.service;


import com.iwork.G629.entity.UserInfo;

public interface UserInfoService {
	/**
	 * 用户登录
	 * @param userName
	 * @param password
	 * @return
	 */
	UserInfo getUserInfoList(String userName,String password);
	
	
	/**
	 * 用户注册
	 * @param userInfo
	 * @return
	 */
	int insertUserInfo(UserInfo userInfo) throws RuntimeException;
	
	/**
	 * 用户修改密码
	 * @param userName
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	int updateUserInfo(String userName,String oldPassword,String newPassword);
	/**
	 * 获取某个用户的全部信息
	 * @param userName
	 * @return
	 */
	UserInfo querySingleUserInfo(String userName);
	
	/**
	 * 更新用户全部信息
	 * @param userInfo
	 * @return
	 */
	int updateUserAllInfo(UserInfo userInfo);
}
