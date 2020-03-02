package com.iwork.G629.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iwork.G629.entity.SelectCondition;

public interface SelectConditionDao {
	/**
	 * 存储用户自定义的查询条件
	 * 
	 * @param Sc
	 * @return
	 */
	int create(@Param("sc") SelectCondition sc);

	/**
	 * 根據itemid來查詢該查詢的條件
	 * 
	 * @param itemId
	 * @return
	 */
	List<SelectCondition> query(String itemId);

	/**
	 * 更新條件信息
	 * 
	 * @param Sc
	 * @return
	 */
	int update(@Param("sc") SelectCondition sc);

	/**
	 * 删除，一般根据当itemId删除时候删除
	 * 
	 * @param Sc
	 * @return
	 */
	int delete(String itemId);
}
