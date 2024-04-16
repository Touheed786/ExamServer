package com.exam.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.exam.model.User;
import com.exam.model.exam.CharDataResponse;
import com.exam.model.exam.Question;
import com.exam.model.exam.Result;

public interface ResultService {
	
	public Result addResult(Result result);
	
	public Set<Result> getResultByUser(User user);
	
	public Set<Result> gellAllResult();
	
	public List<CharDataResponse> getChartData(String year);
	
	public Map<String, Object> evaluateQuiz(List<Question> questions,Long userId);
	
	public List<Long> getYear();
	
	public Result getResultById(int id);
}
