package com.iwork.G629.web.studenachievement;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.iwork.G629.entity.ItemInfo;
import com.iwork.G629.entity.TempAchi;
import com.iwork.G629.entity.UserInfo;
import com.iwork.G629.service.StudentAchievementService;
import com.iwork.G629.service.TempAchiService;
import com.iwork.G629.util.FileUtil;
import com.iwork.G629.util.HttpServletRequestUtil;
import com.iwork.G629.util.QRcode.QRCodeUtil;

@Controller
@RequestMapping("/tp")
public class TempAchiController {
	@Autowired
	private TempAchiService tas;

	@Autowired
	private StudentAchievementService studentAchievementService;

	@RequestMapping(value = "/updateitemname", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	private Map<String, Object> updateitemname(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String itemId = HttpServletRequestUtil.getString(request, "itemId");
		String newItemName = HttpServletRequestUtil.getString(request, "newItemName");
		ItemInfo itemInfo = new ItemInfo();
		itemInfo.setItemId(Integer.parseInt(itemId));
		itemInfo.setItemName(newItemName);
		itemInfo.setLastEditTime(new Date());
		int eff = studentAchievementService.updateItem(itemInfo);
		if (eff == 0) {
			modelMap.put("success", false);
			modelMap.put("Msg", "check the Id");
		} else {
			modelMap.put("success", true);
		}
		return modelMap;
	}

	@RequestMapping(value = "/leaveword", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	private Map<String, Object> leaveword(@RequestBody TempAchi tempAchi) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			int eff = tas.insert(tempAchi);
			if (eff == 0) {
				modelMap.put("success", false);
				modelMap.put("Msg", "result is empty");
			} else {
				modelMap.put("success", true);
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("Msg", e.getMessage());
			e.printStackTrace();
		}
		return modelMap;
	}

	@RequestMapping(value = "/insertexcel", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	private Map<String, Object> insertexcel(HttpServletRequest request) {
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
					int effectedNum = tas.superExcel(FileUtil.transferCommonsMultipartFileToFile(excelFile), ".xlsx",
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

	@RequestMapping(value = "/querybyany", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	private Map<String, Object> queryByAny(@RequestBody List<TempAchi> tempAchi) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			List<List<TempAchi>> taList = new ArrayList<List<TempAchi>>();
			for (int i = 0; i < tempAchi.size(); i++) {
				taList.add(tas.queryByAny(tempAchi.get(i)));
				studentAchievementService.addQueryTimes(tempAchi.get(i).getItemId());
			}
			if (taList.size() == 0) {
				modelMap.put("success", false);
				modelMap.put("Msg", "result is empty");
			} else {
				modelMap.put("success", true);
				modelMap.put("rows", taList);
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("Msg", e.getMessage());
			e.printStackTrace();
		}
		return modelMap;
	}

	@RequestMapping(value = "/querybyitemid", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	private Map<String, Object> querybyitemid(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String itemId = HttpServletRequestUtil.getString(request, "itemId");
		try {
			List<TempAchi> taList = tas.queryByItemId(itemId);
			studentAchievementService.addQueryTimes(itemId);
			if (taList.size() == 0) {
				modelMap.put("success", false);
				modelMap.put("Msg", "result is empty");
			} else {
				modelMap.put("success", true);
				modelMap.put("rows", taList);
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("Msg", e.getMessage());
			e.printStackTrace();
		}
		return modelMap;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	private Map<String, Object> update(@RequestBody TempAchi tempAchi) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			int eff = tas.update(tempAchi);
			if (eff == 0) {
				modelMap.put("success", false);
				modelMap.put("Msg", "result is empty");
			} else {
				modelMap.put("success", true);
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("Msg", e.getMessage());
			e.printStackTrace();
		}
		return modelMap;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	private Map<String, Object> delete(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String Id = HttpServletRequestUtil.getString(request, "Id");
		try {
			int eff = tas.delete(Id);
			if (eff == 0) {
				modelMap.put("success", false);
				modelMap.put("Msg", "result is empty");
			} else {
				modelMap.put("success", true);
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("Msg", e.getMessage());
			e.printStackTrace();
		}
		return modelMap;
	}

	@RequestMapping(value = "/export", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	private Map<String, Object> export(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String itemId = HttpServletRequestUtil.getString(request, "itemId");
		try {
			List<TempAchi> taList = tas.queryByItemId(itemId);
			if (taList.size() == 0) {
				modelMap.put("success", false);
				modelMap.put("Msg", "result is empty");
			} else {
				String Msg = tas.exportToExcel(itemId);
				modelMap.put("success", true);
				modelMap.put("Msg", Msg);
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("Msg", e.getMessage());
			e.printStackTrace();
		}
		return modelMap;
	}

	@RequestMapping(value = "/deletebyitemid", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	private Map<String, Object> deletebyitemid(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String itemId = HttpServletRequestUtil.getString(request, "itemId");
		try {
			int eff = tas.deleteByItemId(itemId);
			if (eff == 0) {
				modelMap.put("success", false);
				modelMap.put("Msg", "result is empty");
			} else {
				modelMap.put("success", true);
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("Msg", e.getMessage());
			e.printStackTrace();
		}
		return modelMap;
	}

}
