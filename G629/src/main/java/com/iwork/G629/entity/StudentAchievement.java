package com.iwork.G629.entity;

public class StudentAchievement {
	private String stuName;// 学生姓名

	private Integer stuId;// 学生学号

	private Integer stuGrade;// 学生所在年级

	private Integer stuClass;// 学生所在班级

	private float achievement = -1f;// 成绩

	private Integer itemId;// 学生所属的查询id

	private String leaveWord;

	private Integer pK;

	public Integer getpK() {
		return pK;
	}

	public void setpK(Integer pK) {
		this.pK = pK;
	}

	public String getLeaveWord() {
		return leaveWord;
	}

	public void setLeaveWord(String leaveWord) {
		this.leaveWord = leaveWord;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public Integer getStuId() {
		return stuId;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}

	public Integer getStuGrade() {
		return stuGrade;
	}

	public void setStuGrade(Integer stuGrade) {
		this.stuGrade = stuGrade;
	}

	public Integer getStuClass() {
		return stuClass;
	}

	public void setStuClass(Integer stuClass) {
		this.stuClass = stuClass;
	}

	public float getAchievement() {
		return achievement;
	}

	public void setAchievement(float achievement) {
		this.achievement = achievement;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
}
