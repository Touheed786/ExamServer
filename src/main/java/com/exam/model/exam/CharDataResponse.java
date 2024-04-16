package com.exam.model.exam;


public class CharDataResponse {
	
	private String key;
	private ChartData chartData;
	
	public CharDataResponse() {
		super();
	}

	public CharDataResponse(String key, ChartData chartData) {
		super();
		this.key = key;
		this.chartData = chartData;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String val) {
		this.key = val;
	}
	public ChartData getChartData() {
		return chartData;
	}
	public void setChartData(ChartData chartData) {
		this.chartData = chartData;
	}

	@Override
	public String toString() {
		return "CharDataResponse [val=" + key + ", chartData=" + chartData + "]";
	}
	

}
