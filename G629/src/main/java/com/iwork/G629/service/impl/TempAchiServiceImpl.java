package com.iwork.G629.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iwork.G629.dao.TempAchiDao;
import com.iwork.G629.entity.TempAchi;
import com.iwork.G629.service.TempAchiService;
import com.iwork.G629.util.ExcelUtil;

@Service
public class TempAchiServiceImpl implements TempAchiService {

	@Autowired
	private TempAchiDao taDao;

	@Override
	public int insert(TempAchi tA) {
		return taDao.insert(tA);
	}

	@Override
	public int update(TempAchi tA) {
		return taDao.update(tA);
	}

	@Override
	public List<TempAchi> queryByItemId(String itemId) {
		return taDao.queryByItemId(itemId);
	}

	@Override
	public List<TempAchi> queryByAny(TempAchi tA) {
		return taDao.queryByAny(tA);
	}

	@Override
	public int delete(String Id) {
		return taDao.delete(Id);
	}

	@Override
	public int superExcel(File file, String extString, int itemId) {
		try {
			FileInputStream excelStream = new FileInputStream(file);
			Workbook wb = ExcelUtil.readExcel(excelStream, extString); // 文件
			Sheet sheet = wb.getSheetAt(0);
			Row hssfRow = sheet.getRow(0);// 获取第一行
			int rowNum = sheet.getLastRowNum();// 获取总行数
			int columnNum = hssfRow.getPhysicalNumberOfCells();// 获取总列数
			String[] propertyName = new String[columnNum];
			String[][] wbs = new String[rowNum + 2][columnNum];
			for (int cp = 0; cp < columnNum; cp++) {
				propertyName[cp] = (String) ExcelUtil.getCellFormatValue(hssfRow.getCell(cp));
				System.out.println("第" + cp + "列名为:" + propertyName[cp]);
				wbs[0][cp] = propertyName[cp];
			}
			System.out.println("总列数:" + columnNum);
			System.out.println("总行数:" + ++rowNum);
			for (int r = 0; r < rowNum; r++) {
				hssfRow = sheet.getRow(r);// 获取第二行
				for (int c = 0; c < columnNum; c++) {
					wbs[r][c] = (String) ExcelUtil.getCellFormatValue(hssfRow.getCell(c));
					System.out.println("第" + r + "行，第" + c + "列的元素为:" + wbs[r][c]);
				}
			}
			List<TempAchi> taList = new ArrayList<TempAchi>();
			for (int rr = 1; rr < rowNum; rr++) {
				for (int cc = 0; cc < columnNum; cc++) {
					TempAchi ta = new TempAchi();
					ta.setPropertyKey(propertyName[cc]);
					ta.setPropertyValue(wbs[rr][cc]);
					ta.setStandardId(itemId + "_" + rr);
					ta.setItemId(Integer.toString(itemId));
					int tempRr = rr - 1;
					taList.add(tempRr + cc, ta);
				}
			}
			taDao.batchInsert(taList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return 0;
		}
		return itemId;
	}

	@Override
	public String exportToExcel(String itemId) {
		List<TempAchi> taList = taDao.queryByItemId(itemId);
		List<TempAchi> temp = new ArrayList<TempAchi>();
		TempAchi tempTa = new TempAchi();
		tempTa.setItemId(itemId);
		tempTa.setStandardId(taList.get(0).getStandardId());
		temp = taDao.queryByAny(tempTa);
		String[] title = new String[temp.size()];
		for (int i = 0; i < temp.size(); i++) {
			title[i] = temp.get(i).getPropertyKey();
			// System.out.println(title[i]);
		}

		// 创建一个工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建一个工作表，名为：第一页
		HSSFSheet sheet = wb.createSheet("sheet1");
		// 创建第一行
		HSSFRow row = sheet.createRow(0);
		// 添加excel title
		HSSFCell cell = null;
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
		}

		HSSFRow[] rowArr = new HSSFRow[taList.size() / title.length + 1];

		for (int i = 1; i < rowArr.length; i++) {
			rowArr[i] = sheet.createRow(i);
		}

		if (taList.size() == 0) {
			return "failed:" + "找不到该查询，请检查itemId";
		} else {
			// 第四步，创建单元格，并设置值
			for (int i = 0; i < taList.size(); i++) {
				for (int j = 0; j < title.length; j++) {
					if (taList.get(i).getPropertyKey().equals(title[j])) {
						rowArr[Integer.parseInt(taList.get(i).getStandardId().replaceAll(itemId + "_", ""))]
								.createCell(j).setCellValue(taList.get(i).getPropertyValue());
					}
				}
			}
		}

		try {
			FileOutputStream fout = new FileOutputStream("C:\\projectdev\\QRCodeImage\\" + itemId + ".xls");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";

	}

	@Override
	public int deleteByItemId(String itemId) {
		return taDao.deleteByItemId(itemId);
	}

}
