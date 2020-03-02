package com.iwork.G629.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iwork.G629.dao.SelectConditionDao;
import com.iwork.G629.entity.SelectCondition;
import com.iwork.G629.service.SelectConditionService;

@Service
public class SelectConditionServiceImpl implements SelectConditionService {

	@Autowired
	private SelectConditionDao scDao;

	@Override
	public String create(SelectCondition sc) {
		scDao.create(sc);
		return "success";
	}

	@Override
	public List<SelectCondition> query(String itemId) {
		return scDao.query(itemId);
	}

	@Override
	public String update(SelectCondition sc) {
		int effectedNum = scDao.update(sc);
		if (effectedNum != 0) {
			return "success";
		} else {
			return "failed: check the conditionId or itemId";
		}
	}

	@Override
	public String delete(String itemId) {
		if (scDao.delete(itemId) != 0) {
			return "success";
		} else {
			return "failed:check the itemId";
		}
	}

}
