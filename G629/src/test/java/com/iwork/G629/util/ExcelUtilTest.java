package com.iwork.G629.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

public class ExcelUtilTest {
	@Test
	public void testReadExcel() {
		/**
		 * X列数 Y开始的行数 Z结束的行数
		 */
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream("C:/Users/曾越爸爸/Desktop/1111.xlsx");
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		String[] columnSet = ExcelUtil.getColumnArray(fileInputStream, ".xlsx", /* X */1, /* Y */ 1, /* Z */30);
		for (int i = 0; i < columnSet.length; i++) {
			System.out.println("第" + i + "行元素：" + columnSet[i]);
		}
	}

}
