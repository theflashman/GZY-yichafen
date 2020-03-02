package com.iwork.G629.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iwork.G629.entity.TempAchi;

public interface TempAchiDao {
	int insert(@Param("tA") TempAchi tA);

	int batchInsert(List<TempAchi> tempAchiList);

	int update(@Param("tA") TempAchi tA);

	List<TempAchi> queryByItemId(@Param("itemId") String itemId);

	List<TempAchi> queryByAny(@Param("tA") TempAchi tA);

	int delete(String Id);

	int deleteByItemId(String itemId);
}
