package com.iwork.G629.service;

import java.io.File;
import java.util.List;

import com.iwork.G629.entity.TempAchi;

public interface TempAchiService {
	int insert(TempAchi tA);

	int update(TempAchi tA);

	List<TempAchi> queryByItemId(String itemId);

	List<TempAchi> queryByAny(TempAchi tA);

	int delete(String Id);

	int superExcel(File file, String extString, int itemId);

	String exportToExcel(String itemId);

	int deleteByItemId(String itemId);
}
