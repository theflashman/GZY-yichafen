package com.iwork.G629.web.studenachievement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwork.G629.entity.SelectCondition;
import com.iwork.G629.service.SelectConditionService;
import com.iwork.G629.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/condition")

public class SelectConditionController {
	@Autowired
	private SelectConditionService selectConditionService;

	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	private Map<String, Object> create(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String conditionItemId = HttpServletRequestUtil.getString(request, "conditionItemId");
		String conditionName = HttpServletRequestUtil.getString(request, "conditionName");
		String conditionTip = HttpServletRequestUtil.getString(request, "conditionTip");
		String conditionStart = HttpServletRequestUtil.getString(request, "conditionStart");
		String conditionFinal = HttpServletRequestUtil.getString(request, "conditionFinal");
		SelectCondition sc = new SelectCondition();
		sc.setConditionFinal(conditionFinal);
		sc.setConditionItemId(conditionItemId);
		sc.setConditionName(conditionName);
		sc.setConditionStart(conditionStart);
		sc.setConditionTip(conditionTip);
		try {
			selectConditionService.create(sc);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	private Map<String, Object> update(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String conditionId = HttpServletRequestUtil.getString(request, "conditionId");
		String conditionItemId = HttpServletRequestUtil.getString(request, "conditionItemId");
		String conditionName = HttpServletRequestUtil.getString(request, "conditionName");
		String conditionTip = HttpServletRequestUtil.getString(request, "conditionTip");
		String conditionStart = HttpServletRequestUtil.getString(request, "conditionStart");
		String conditionFinal = HttpServletRequestUtil.getString(request, "conditionFinal");
		SelectCondition sc = new SelectCondition();
		sc.setConditionFinal(conditionFinal);
		sc.setConditionItemId(conditionItemId);
		sc.setConditionName(conditionName);
		sc.setConditionStart(conditionStart);
		sc.setConditionTip(conditionTip);
		sc.setConditionId(conditionId);
		try {
			String Msg = selectConditionService.update(sc);
			if (Msg.equals("success")) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("Msg", Msg);
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@ResponseBody
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	private Map<String, Object> query(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String conditionItemId = HttpServletRequestUtil.getString(request, "conditionItemId");
		try {
			List<SelectCondition> scList = selectConditionService.query(conditionItemId);
			if (scList != null && scList.size() != 0) {
				modelMap.put("success", true);
				modelMap.put("rows", scList);
			} else {
				modelMap.put("success", false);
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	private Map<String, Object> delete(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String conditionItemId = HttpServletRequestUtil.getString(request, "conditionItemId");
		try {
			String Msg = selectConditionService.delete(conditionItemId);
			if (Msg.equals("success")) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("Msg", Msg);
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
}
