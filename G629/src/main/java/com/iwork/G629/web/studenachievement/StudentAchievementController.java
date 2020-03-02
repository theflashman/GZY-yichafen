package com.iwork.G629.web.studenachievement;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.iwork.G629.entity.ItemInfo;
import com.iwork.G629.entity.StudentAchievement;
import com.iwork.G629.entity.UserInfo;
import com.iwork.G629.service.StudentAchievementService;
import com.iwork.G629.util.FileUtil;
import com.iwork.G629.util.HttpServletRequestUtil;
import com.iwork.G629.util.QRcode.QRCodeUtil;

@Controller
@RequestMapping("/studentachievement")
public class StudentAchievementController {
	@Autowired
	private StudentAchievementService studentAchievementService;

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = { "application/json;charset=utf-8" })
	public String getWxUserInfo(HttpServletRequest request, @RequestParam(required = false) String echostr,
			@RequestParam(required = false) String signature, @RequestParam(required = false) String timestamp,
			@RequestParam(required = false) String nonce) {
		try {
			return echostr;
		} catch (Exception e) {
			return "错误！！！";
		}

	}

	@RequestMapping(value = "/wechat", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String authGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String msg = null;
		String buffer = null;
		StringBuffer xml = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			while ((buffer = br.readLine()) != null) {
				xml.append(buffer);
				System.out.println(buffer);
			}
			msg = xml.substring(xml.indexOf("<Content><![CDATA[") + 18, xml.indexOf("]]></Content>"));
			System.out.println(msg);
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		String FromUserName = xml.substring(xml.indexOf("<ToUserName><![CDATA[") + 21, xml.indexOf("]]></ToUserName>"));
		String ToUserName = xml.substring(xml.indexOf("<FromUserName><![CDATA[") + 23,
				xml.indexOf("]]></FromUserName>"));
		String returnMsg = "<xml>" + "<ToUserName><![CDATA[" + ToUserName + "]]></ToUserName>\r\n"
				+ "<FromUserName><![CDATA[" + FromUserName + "]]></FromUserName>\r\n"
				+ "<CreateTime>1573044943</CreateTime>\r\n" + "<MsgType><![CDATA[text]]></MsgType>\r\n"
				+ "<Content><![CDATA[" + msg + "]]></Content>\r\n" + "<MsgId>22520588552514511</MsgId>\r\n" + "</xml>";
		response.getWriter().print(returnMsg);
		return HttpServletRequestUtil.getString(request, "echostr");
	}

	@RequestMapping(value = "/deleteitembyitemid", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	private Map<String, Object> deleteItemByItemId(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			String itemName = HttpServletRequestUtil.getString(request, "itemName");
			String userName = HttpServletRequestUtil.getString(request, "userName");
			int itemId = HttpServletRequestUtil.getInt(request, "itemId");
			int tempItemId = studentAchievementService.queryExcelFormItemId(itemName, userName);
			if (itemId == tempItemId) {
				studentAchievementService.deleteStudentAchievementByItemId(itemId);
				try {
					int effecetedNum2 = studentAchievementService.deleteItem(itemId);
					if (effecetedNum2 != 0) {
						modelMap.put("success", true);
					} else {
						modelMap.put("success", false);
						modelMap.put("errMsg", "请检查是否有权限删除该查询");
					}
				} catch (Exception e) {
					modelMap.put("success", false);
					modelMap.put("errMsg", e.getMessage());
				}

			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "请检查是否有权限删除该查询");
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value = "/getsinglestudentachievement", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listUserInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<StudentAchievement> stu;
		try {
			Integer stuId = HttpServletRequestUtil.getInt(request, "stuId");
			String stuName = HttpServletRequestUtil.getString(request, "stuName");
			String userName = HttpServletRequestUtil.getString(request, "userName");
			stu = studentAchievementService.getStudentAchievementBystuId(stuId, stuName, userName);
			if (stu == null) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "没有查询到数据");
			} else {
				modelMap.put("rows", stu);
				modelMap.put("success", true);
			}
			// modelMap.put("id", stu.getStuId());
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		return modelMap;
	}

	@RequestMapping(value = "/batchinsertstudentachievementbyexcel", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	private Map<String, Object> batchInsertStudentAchievementByExcel(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			CommonsMultipartFile excelFile = null;
			String userName = HttpServletRequestUtil.getString(request, "userName");
			String itemName = HttpServletRequestUtil.getString(request, "itemName");
			if (studentAchievementService.queryExcelFormItemId(itemName, userName) != null) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "该查询已存在！请检查名称是否正确");
				return modelMap;
			}
			UserInfo userInfo = studentAchievementService.querySingleUserInfo(userName);
			ItemInfo itemInfo = new ItemInfo();
			itemInfo.setCreateTime(new Date());
			itemInfo.setItemName(itemName);
			itemInfo.setLastEditTime(new Date());
			itemInfo.setUserId(userInfo.getUserId());
			itemInfo.setUserName(userInfo.getUserName());
			try {
				CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
						request.getSession().getServletContext());
				MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
				excelFile = (CommonsMultipartFile) multipartHttpServletRequest.getFile("excelFile");
				if (commonsMultipartResolver.isMultipart(request) && excelFile != null) {

				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", "上传的Excel文件不能为空");
					return modelMap;
				}
				try {
					studentAchievementService.insertExcelFormInfo(itemInfo);
					int effectedNum = studentAchievementService.batchInsertStudentAchievementByExcel(
							FileUtil.transferCommonsMultipartFileToFile(excelFile), ".xlsx",
							studentAchievementService.queryExcelFormItemId(itemName, userName));
					Integer itemId = studentAchievementService.queryExcelFormItemId(itemName, userName);
					if (effectedNum == 0) {
						studentAchievementService.deleteItem(itemId);
						modelMap.put("success", false);
						modelMap.put("errMsg", "插入失败，请检查表格格式是否正确");
						return modelMap;
					}
					String imgPath = "C:\\projectdev\\QRCodeImage\\";
					String QRCodePath = imgPath + userName + ".jpg";
					// 后续需要修改下面的网址
					QRCodeUtil.encode("http://47.107.90.185/dist/#/mobile?userName=" + userName, null, QRCodePath,
							true);
					modelMap.put("QRCode", new File(QRCodePath));
					modelMap.put("success", true);
					modelMap.put("itemId", itemId);
					// modelMap.put("id", stu.getStuId());
				} catch (Exception e) {
					e.printStackTrace();
					modelMap.put("success", false);
					modelMap.put("errMsg", e.getMessage());
				}
			} catch (Exception e) {
				e.printStackTrace();
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value = "/queryiteminfolist", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> queryItemInfoList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			String userName = HttpServletRequestUtil.getString(request, "userName");
			List<ItemInfo> itemInfoList = studentAchievementService.queryAllItemInfoByUserName(userName);
			modelMap.put("rows", itemInfoList);
			if (itemInfoList != null) {
				modelMap.put("rows", itemInfoList);
				modelMap.put("success", true);
				return modelMap;
			} else {
				modelMap.put("success", false);
				return modelMap;
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			return modelMap;
		}

	}

	@RequestMapping(value = "/updatestudentachievement", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> batchUpdateStudenAchievement(
			@RequestBody List<StudentAchievement> studentAchievementList, HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			studentAchievementService.batchUpdateStudentAchievement(studentAchievementList);
			modelMap.put("success", true);
		} catch (RuntimeException e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value = "/queryallstudentachievementbyitemid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> queryAllStudentAchievementByItemId(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int itemId = HttpServletRequestUtil.getInt(request, "itemid");
		int status = HttpServletRequestUtil.getInt(request, "status");
		try {
			List<StudentAchievement> studentAchievementList = studentAchievementService
					.queryAllstudentAchievementByItemId(itemId, status);
			if (studentAchievementList.size() == 0) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "没有查询到结果");
			} else {
				modelMap.put("success", true);
				modelMap.put("rows", studentAchievementList);
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value = "/queryitembyitemname", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> queryItemByItemName(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String itemName = HttpServletRequestUtil.getString(request, "itemName");
		String userName = HttpServletRequestUtil.getString(request, "userName");
		List<ItemInfo> itemInfoList = studentAchievementService.queryItemByItemName(itemName, userName);
		if (itemInfoList.size() == 0) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "没有查询到结果");
		} else {
			modelMap.put("success", true);
			modelMap.put("rows", itemInfoList);
		}
		return modelMap;
	}

	@RequestMapping(value = "/querybystuidanditemid", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	private Map<String, Object> querybystuidanditemid(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String itemId = HttpServletRequestUtil.getString(request, "itemId");
		String stuId = HttpServletRequestUtil.getString(request, "stuId");
		try {
			List<StudentAchievement> as = studentAchievementService.queryByStuIdAndName(stuId, itemId);
			if (as.size() == 0) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "查找不到数据");
			} else {
				modelMap.put("success", true);
				modelMap.put("studentAchievement", as);
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value = "/exportexcel", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	private Map<String, Object> exportexcel(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String itemId = HttpServletRequestUtil.getString(request, "itemId");
		try {
			String Msg = studentAchievementService.exportToExcel(itemId);
			if (Msg.equals("success")) {
				modelMap.put("success", true);
				modelMap.put("Url", "qrcode/" + itemId + ".xls");
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", Msg);
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value = "/querybyuser", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	private Map<String, Object> querybyuser(@RequestBody StudentAchievement studentAchievement) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			List<StudentAchievement> as = studentAchievementService.queryByUser(studentAchievement);
			modelMap.put("success", true);
			modelMap.put("rows", as);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
}
