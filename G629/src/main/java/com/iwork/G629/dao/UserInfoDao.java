package com.iwork.G629.dao;

import org.apache.ibatis.annotations.Param;

import com.iwork.G629.entity.UserInfo;

public interface UserInfoDao {
	/**
	 * 获取该用户名下用户的所有信息
	 * @param userName
	 * @return
	 */
	UserInfo querySingleUserInfo(@Param("userName") String userName);
	/**
	 * 注册用户
	 * 
	 * @param userInfo
	 * @return
	 */
	int insertUserInfo(UserInfo userInfo);

	/**
	 * 列出用户的所有信息
	 * 
	 * @return
	 */
	UserInfo queryUserInfo(@Param("userName") String userName, @Param("password") String password);

	
	/**
	 * 用户修改密码
	 * @param userName
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	int updateUserInfo(@Param("userName") String userName, @Param("oldPassword") String oldPassword,
			@Param("newPassword") String newPassword);
	
	
	/**
	 * 更新整个userInfo
	 * @param userInfo
	 * @return
	 */
	int updateUserAllInfo(@Param("userInfo")UserInfo userInfo);
}
