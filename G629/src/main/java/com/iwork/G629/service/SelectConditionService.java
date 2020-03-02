package com.iwork.G629.service;

import java.util.List;

import com.iwork.G629.entity.SelectCondition;

public interface SelectConditionService {
	/**
	 * 存储用户自定义的查询条件
	 * 
	 * @param Sc
	 * @return
	 */
	public String create(SelectCondition sc);

	/**
	 * 根據itemid來查詢該查詢的條件
	 * 
	 * @param itemId
	 * @return
	 */
	public List<SelectCondition> query(String itemId);

	/**
	 * 更新條件信息
	 * 
	 * @param Sc
	 * @return
	 */
	public String update(SelectCondition sc);

	/**
	 * 删除，一般根据当itemId删除时候删除
	 * 
	 * @param Sc
	 * @return
	 */
	public String delete(String itemId);
}
