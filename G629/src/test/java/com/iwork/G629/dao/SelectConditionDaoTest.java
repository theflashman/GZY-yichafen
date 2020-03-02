package com.iwork.G629.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwork.G629.BaseTest;
import com.iwork.G629.entity.SelectCondition;

public class SelectConditionDaoTest extends BaseTest {
	@Autowired
	private SelectConditionDao scDao;

	@Test
	public void testA() {
		SelectCondition sc = new SelectCondition();
		sc.setConditionFinal("123");
		sc.setConditionItemId("123123");
		sc.setConditionName("456");
		sc.setConditionStart("qwe");
		sc.setConditionTip("321");
		scDao.create(sc);
	}

	@Test
	public void testB() {
		List<SelectCondition> scList = scDao.query("123123");
		for (int i = 0; i < scList.size(); i++) {
			System.out.println(scList.get(i).getConditionFinal());
			System.out.println(scList.get(i).getConditionId());
			System.out.println(scList.get(i).getConditionItemId());
			System.out.println(scList.get(i).getConditionName());
			System.out.println(scList.get(i).getConditionStart());
			System.out.println(scList.get(i).getConditionTip());
		}
	}

	@Test
	public void testC() {
		SelectCondition sc = new SelectCondition();
		sc.setConditionFinal("123dadasda");
		sc.setConditionId("1");
		sc.setConditionItemId("123123");
		// sc.setConditionName("456dadadasdad");
		sc.setConditionStart("dasdasaasdasdsa");
		sc.setConditionTip("31");
		scDao.update(sc);
	}

	@Test
	public void testD() {
		scDao.delete("123123");
	}
}
