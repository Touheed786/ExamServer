package com.exam.model.exam;

public class ChartData {
	private String date;
	private int totalCount;
	private String color;
	private int passCount;
	private int failCount;
	
	
	
	public ChartData(String date, int totalCount, String color, int passCount, int failCount) {
		super();
		this.date = date;
		this.totalCount = totalCount;
		this.color = color;
		this.passCount = passCount;
		this.failCount = failCount;
	}
	public ChartData() {
		// TODO Auto-generated constructor stub
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getPassCount() {
		return passCount;
	}
	public void setPassCount(int passCount) {
		this.passCount = passCount;
	}
	public int getFailCount() {
		return failCount;
	}
	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
	
}
