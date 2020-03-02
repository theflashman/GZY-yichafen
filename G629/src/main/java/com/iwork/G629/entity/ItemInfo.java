package com.iwork.G629.entity;

import java.util.Date;

public class ItemInfo {
	private Integer itemId;
	private String itemName;
	private Integer userId;
	private String userName;
	private Date createTime;
	private Date lastEditTime;
	private int queryTimes;

	public int getQueryTimes() {
		return queryTimes;
	}

	public void setQueryTimes(int queryTimes) {
		this.queryTimes = queryTimes;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
}
