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

import com.iwork.G629.dao.StudentAchievementDao;
import com.iwork.G629.entity.ItemInfo;
import com.iwork.G629.entity.StudentAchievement;
import com.iwork.G629.entity.UserInfo;
import com.iwork.G629.service.StudentAchievementService;
import com.iwork.G629.util.ExcelUtil;

@Service
public class StudentAchievementServiceImpl implements StudentAchievementService {
	@Autowired
	private StudentAchievementDao studentAchievementDao;

	@Override
	public List<StudentAchievement> getStudentAchievementBystuId(Integer stuId, String stuName, String userName) {
		return studentAchievementDao.queryStudentAchievementBystuId(stuId, stuName, userName);
	}

	@Override
	public int batchInsertStudentAchievementByExcel(File file, String extString, int itemId) {
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
			List<StudentAchievement> studentAchievementList = new ArrayList<StudentAchievement>();
			StudentAchievement[] studentAchievement = new StudentAchievement[rowNum - 1];
			for (int rr = 1; rr < rowNum; rr++) {
				StudentAchievement tempstudentAchievement = new StudentAchievement();
				tempstudentAchievement.setItemId(itemId);
				for (int cc = 0; cc < columnNum; cc++) {
					if (propertyName[cc].equals("stuId")) {
						tempstudentAchievement.setStuId(Integer.parseInt(wbs[rr][cc]));
					} else if (propertyName[cc].equals("stuName")) {
						tempstudentAchievement.setStuName(wbs[rr][cc]);
					} else if (propertyName[cc].equals("stuGrade")) {
						tempstudentAchievement.setStuGrade(Integer.valueOf(wbs[rr][cc]));
					} else if (propertyName[cc].equals("stuClass")) {
						tempstudentAchievement.setStuClass(Integer.valueOf(wbs[rr][cc]));
					} else if (propertyName[cc].equals("achievement")) {
						tempstudentAchievement.setAchievement(Float.parseFloat(wbs[rr][cc]));
					}
				}
				studentAchievement[rr - 1] = tempstudentAchievement;
			}
			for (int rrr = 0; rrr < rowNum - 1; rrr++) {
				studentAchievementList.add(studentAchievement[rrr]);
			}
			try {
				int effectedNum = studentAchievementDao.batchInsertStudentAchievement(studentAchievementList);

				return effectedNum;
			} catch (RuntimeException e) {
				return 0;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return 0;
		}
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
			List<StudentAchievement> studentAchievementList = new ArrayList<StudentAchievement>();
			StudentAchievement[] studentAchievement = new StudentAchievement[rowNum - 1];
			for (int rr = 1; rr < rowNum; rr++) {
				StudentAchievement tempstudentAchievement = new StudentAchievement();
				tempstudentAchievement.setItemId(itemId);
				for (int cc = 0; cc < columnNum; cc++) {
					if (propertyName[cc].equals("stuId")) {
						tempstudentAchievement.setStuId(Integer.parseInt(wbs[rr][cc]));
					} else if (propertyName[cc].equals("stuName")) {
						tempstudentAchievement.setStuName(wbs[rr][cc]);
					} else if (propertyName[cc].equals("stuGrade")) {
						tempstudentAchievement.setStuGrade(Integer.valueOf(wbs[rr][cc]));
					} else if (propertyName[cc].equals("stuClass")) {
						tempstudentAchievement.setStuClass(Integer.valueOf(wbs[rr][cc]));
					} else if (propertyName[cc].equals("achievement")) {
						tempstudentAchievement.setAchievement(Float.parseFloat(wbs[rr][cc]));
					}
				}
				studentAchievement[rr - 1] = tempstudentAchievement;
			}
			for (int rrr = 0; rrr < rowNum - 1; rrr++) {
				studentAchievementList.add(studentAchievement[rrr]);
			}
			try {
				int effectedNum = studentAchievementDao.batchInsertStudentAchievement(studentAchievementList);

				return effectedNum;
			} catch (RuntimeException e) {
				return 0;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int insertExcelFormInfo(ItemInfo itemInfo) {
		int effectedNum = studentAchievementDao.insertExcelFormInfo(itemInfo);
		if (effectedNum > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public UserInfo querySingleUserInfo(String userName) {
		// TODO 自动生成的方法存根
		return studentAchievementDao.querySingleUserInfo(userName);
	}

	@Override
	public List<ItemInfo> queryAllItemInfoByUserName(String userName) {
		return studentAchievementDao.queryItemInfoList(userName);
	}

	@Override
	public Integer queryExcelFormItemId(String itemName, String userName) {
		return studentAchievementDao.queryItemId(itemName, userName);
	}

	@Override
	public int batchUpdateStudentAchievement(List<StudentAchievement> studentAchievementList) {
		return studentAchievementDao.batchUpdateStudentAchievement(studentAchievementList);
	}

	@Override
	public List<StudentAchievement> queryAllstudentAchievementByItemId(int itemId, int status) {
		List<StudentAchievement> studentAchievementList = new ArrayList<>();
		studentAchievementList = studentAchievementDao.queryAllstudentAchievementByItemId(itemId);
		return studentAchievementList;
	}

	@Override
	public int deleteStudentAchievementByItemId(int itemId) {
		return studentAchievementDao.deleteStudentAchievementByItemId(itemId);
	}

	@Override
	public int deleteItem(int itemId) {
		return studentAchievementDao.deleteItem(itemId);
	}

	@Override
	public List<ItemInfo> queryItemByItemName(String itemName, String userName) {
		return studentAchievementDao.queryItemByItemName(itemName, userName);
	}

	@Override
	public List<StudentAchievement> queryByStuIdAndName(String stuId, String itemId) {
		return studentAchievementDao.queryByStuIdAndName(itemId, stuId);
	}

	@Override
	public String exportToExcel(String itemId) {
		String[] title = new String[5];
		title[0] = "stuId";
		title[1] = "stuName";
		title[2] = "stuGrade";
		title[3] = "stuClass";
		title[4] = "achievement";
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

		List<StudentAchievement> stuList = studentAchievementDao
				.queryAllstudentAchievementByItemId(Integer.parseInt(itemId));
		if (stuList == null) {
			return "failed:" + "找不到该查询，请检查itemId";
		} else {
			// 第四步，创建单元格，并设置值
			for (int i = 0; i < stuList.size(); i++) {
				row = sheet.createRow(i + 1);
				for (int j = 0; j < title.length; j++) {
					row.createCell(j).setCellValue(stuList.get(i).getStuId().toString());
					row.createCell(j = j + 1).setCellValue(stuList.get(i).getStuName());
					row.createCell(j = j + 1).setCellValue(stuList.get(i).getStuGrade());
					row.createCell(j = j + 1).setCellValue(stuList.get(i).getStuClass());
					row.createCell(j = j + 1).setCellValue(stuList.get(i).getAchievement());
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
	public List<StudentAchievement> queryByUser(StudentAchievement studentAchievement) {
		List<StudentAchievement> saList = studentAchievementDao.queryByUser(studentAchievement);
		return saList;
	}

	@Override
	public int addQueryTimes(String itemId) {
		return studentAchievementDao.addQueryTimes(itemId);
	}

	@Override
	public int updateItem(ItemInfo item) {
		return studentAchievementDao.updateItem(item);
	}

}
