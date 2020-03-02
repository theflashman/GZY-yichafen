package com.iwork.G629.web.superadmin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwork.G629.entity.UserInfo;
import com.iwork.G629.service.UserInfoService;
import com.iwork.G629.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/superadmin")
public class UserInfoController {
	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping(value = "/getsingleuserinfo", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> singleUserInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			String userName = HttpServletRequestUtil.getString(request, "userName");
			String password = HttpServletRequestUtil.getString(request, "password");
			UserInfo userInfo = userInfoService.getUserInfoList(userName, password);
			if (userInfo != null) {
				modelMap.put("success", true);
				modelMap.put("userInfo", userInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		return modelMap;
	}

	@RequestMapping(value = "/registersingleuserinfo", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerUserInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		UserInfo userInfo = new UserInfo();
		try {
			String userName = HttpServletRequestUtil.getString(request, "userName");
			String password = HttpServletRequestUtil.getString(request, "password");
			int status = HttpServletRequestUtil.getInt(request, "status");
			String email = HttpServletRequestUtil.getString(request, "email");
			String unit = HttpServletRequestUtil.getString(request, "unit");
			int qq = HttpServletRequestUtil.getInt(request, "qq");
			int level = HttpServletRequestUtil.getInt(request, "level");
			String personalName = HttpServletRequestUtil.getString(request, "personalName");

			userInfo.setCreateTime(new Date());
			userInfo.setUserName(userName);
			userInfo.setPassword(password);
			userInfo.setStatus(status);
			userInfo.setEmail(email);
			userInfo.setLevel(level);
			userInfo.setPersonalName(personalName);
			userInfo.setQq(qq);
			userInfo.setUnit(unit);
			int effectedNum = userInfoService.insertUserInfo(userInfo);
			if (effectedNum != 0) {
				modelMap.put("success", true);
				modelMap.put("rows", userInfo);
			} else {
				modelMap.put("success", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		return modelMap;
	}

	@RequestMapping(value = "/updateuserpassword", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> updateUserPassword(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			String userName = HttpServletRequestUtil.getString(request, "userName");
			String oldPassword = HttpServletRequestUtil.getString(request, "oldPassword");
			String newPassword = HttpServletRequestUtil.getString(request, "newPassword");
			int effectedNum = userInfoService.updateUserInfo(userName, oldPassword, newPassword);
			if (effectedNum != 0) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
			}
		} catch (RuntimeException e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getStackTrace().toString());
			return modelMap;
		}
		return modelMap;
	}

	@RequestMapping(value = "/updateuserallinfo", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> updateUserAllInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		UserInfo userInfo = new UserInfo();
		try {
			String userName = HttpServletRequestUtil.getString(request, "userName");
			int status = HttpServletRequestUtil.getInt(request, "status");
			String email = HttpServletRequestUtil.getString(request, "email");
			String unit = HttpServletRequestUtil.getString(request, "unit");
			int qq = HttpServletRequestUtil.getInt(request, "qq");
			int level = HttpServletRequestUtil.getInt(request, "level");
			String personalName = HttpServletRequestUtil.getString(request, "personalName");

			userInfo.setCreateTime(new Date());
			userInfo.setUserName(userName);
			userInfo.setStatus(status);
			userInfo.setEmail(email);
			userInfo.setLevel(level);
			userInfo.setPersonalName(personalName);
			userInfo.setQq(qq);
			userInfo.setUnit(unit);
			int effectedNum = userInfoService.updateUserAllInfo(userInfo);
			if (effectedNum != 0) {
				modelMap.put("success", true);
				modelMap.put("rows", userInfo);
			} else {
				modelMap.put("success", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		return modelMap;
	}
}
