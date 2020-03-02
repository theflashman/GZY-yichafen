package com.iwork.G629.service;

import java.io.File;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwork.G629.BaseTest;
import com.iwork.G629.entity.TempAchi;

public class TempAchiServiceTest extends BaseTest {
	@Autowired
	private TempAchiService taS;

	@Test
	@Ignore
	public void testA() {
		TempAchi tA = new TempAchi();
		tA.setItemId("123");
		tA.setPropertyKey("sdasdasdasda");
		tA.setPropertyValue("dsadsadsa");
		tA.setStandardId("dadsadsa");
		taS.insert(tA);
	}

	@Test
	@Ignore
	public void testB() {
		TempAchi tA = new TempAchi();
		tA.setId("0");
		tA.setItemId("123");
		tA.setPropertyKey("s");
		tA.setPropertyValue("d");
		tA.setStandardId("a");
		taS.update(tA);
	}

	@Test
	@Ignore
	public void testC() {
		List<TempAchi> list = taS.queryByItemId("123");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getId());
			System.out.println(list.get(i).getPropertyKey());
			System.out.println(list.get(i).getPropertyValue());
			System.out.println(list.get(i).getStandardId());
			System.out.println(list.get(i).getItemId());
		}

		TempAchi tA = new TempAchi();
		tA.setItemId("123");
		tA.setPropertyKey("s");
		tA.setPropertyValue("d");
		tA.setStandardId("a");
		list = taS.queryByAny(tA);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getId());
			System.out.println(list.get(i).getPropertyKey());
			System.out.println(list.get(i).getPropertyValue());
			System.out.println(list.get(i).getStandardId());
			System.out.println(list.get(i).getItemId());
		}
	}

	@Test
	@Ignore
	public void testD() {
		taS.delete("0");
	}

	@Test
	@Ignore
	public void testE() {
		taS.superExcel(
				new File("C:\\Users\\曾越爸爸\\Desktop\\8c98a63adb9f4d0cebd41ad5dcd6d793\\新建 Microsoft Excel 工作表.xlsx"),
				".xlsx", 123);
	}

	@Test
	public void testF() {
		taS.exportToExcel("123");
	}
}
