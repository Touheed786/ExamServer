package com.exam.service.impl;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.exam.CharDataResponse;
import com.exam.model.exam.ChartData;
import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.model.exam.Result;
import com.exam.repo.ResultRepository;
import com.exam.service.QuestionService;
import com.exam.service.ResultService;
import com.exam.service.UserService;

@Service
public class ResultServiceImpl implements ResultService {

	@Autowired
	private ResultRepository resultRepository;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public Result addResult(Result result) {
		// TODO Auto-generated method stub
		return this.resultRepository.save(result);
	}

	@Override
	public Set<Result> getResultByUser(User user) {
		// TODO Auto-generated method stub
		return resultRepository.findByUser(user);
	}

	@Override
	public Set<Result> gellAllResult() {
		// TODO Auto-generated method stub
		
		return new HashSet<>(resultRepository.findAll());
	}
	
	@Override
	public Map<String, Object> evaluateQuiz(List<Question> questions, Long userId) {
		// TODO Auto-generated method stub
		String quizTitle = questions.get(0).getQuiz().getTitle();
		Long quizid = questions.get(0).getQuiz().getqId();

		int marksGot =0;
		int correctedAnswer=0;
		int attempted=0;
		int numberOfQuestion=questions.size();
		double quizMarks =0;
		float percentage =0;
		for (Question ques : questions) {
//			single question
			Question question = questionService.getQuestion(ques.getQuesId());
			if (question.getAnswer().equals(ques.getGivenAnswer()))
			{
				correctedAnswer++;
			}
			if(ques.getGivenAnswer()!= null)
			{
				attempted++;
			}
		}
		quizMarks = Integer.parseInt(questions.get(0).getQuiz().getMaxMArks());
		marksGot = (int) (quizMarks / numberOfQuestion * correctedAnswer);
		percentage = (float) ((marksGot / quizMarks) * 100);
		
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("quizTitle", quizTitle);
		map.put("marksGot", marksGot);
		map.put("correctedAnswer", correctedAnswer);
		map.put("attempted", attempted);
		map.put("numberOfQuestion", numberOfQuestion);
		
		SimpleDateFormat simpleformat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");

		User user = new User();
		user.setId(userId);
		Quiz quiz = new Quiz();
		quiz.setqId(quizid);
		
		Result result = new Result();
		result.setQuiz_type(quizTitle);
		result.setMarksGot(marksGot);
		result.setCorrectedAnswer(correctedAnswer);
		result.setAttempted(attempted);
		result.setNumberOfQuestion(numberOfQuestion);
		result.setUser(user);
		result.setQuiz(quiz);
//		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		Date date = new Date();
		LocalDate currentDate = LocalDate.now();
        
        // Format the current date to a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = currentDate.format(formatter);
        result.setDate(date);
        
        
        simpleformat = new SimpleDateFormat("MMMM");  
        String month= simpleformat.format(new Date());
        result.setMonth(month);
        
        simpleformat = new SimpleDateFormat("YYYY");  
        String year= simpleformat.format(new Date());
        result.setYear(year);
		String resultSatatus = "You have Failed The Exam";
		String mail = "";
		if(percentage>=35F)
		{
			result.setTest_result(true);
			resultSatatus = "Congratulations.! You have clear the exam";
		}
		result.setTest_result(result.getTest_result());
		
		mail = userService.getUserById(userId).getEmail();
		if(mail != "")
			emailService.sendMail(mail, "Regarding Result", resultSatatus);
		
		this.resultRepository.save(result);
		
		System.out.println("Percentage :"+percentage);
		System.out.println("Result Added Successfully...Hurrreeee");
		return map;
	}
	
	
	
	@Override
	public List<CharDataResponse> getChartData(String selectedYear) {
		
		List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
		
		List<CharDataResponse> charDataResponses = new ArrayList<>();
		Map<String, ChartData> map = new HashMap<>();
		Set<Result> allResult= resultRepository.findByYear(selectedYear);
		
		for(Result result:allResult) {
			String month = result.getMonth();
            boolean testResult = result.getTest_result();
            ChartData chartData = map.getOrDefault(month, new ChartData());
            
            if(map.containsKey(month)) {
            	
            	if(testResult) {
//				chartData.getPassCount();
            		map.put(month,new ChartData(month,(map.get(month).getPassCount()+1) + (chartData.getFailCount()),"green",map.get(month).getPassCount()+1,chartData.getFailCount()));
            	}else {
//				chartData.getFailCount();
            		map.put(month,new ChartData(month,(chartData.getPassCount()) + (map.get(month).getFailCount()+1),"green",chartData.getPassCount(),map.get(month).getFailCount()+1));
            	}
            }else {
            	if(testResult) {
//    				chartData.getPassCount();
                		map.put(month,new ChartData(month,1,"green",1,chartData.getFailCount()));
                	}else {
//    				chartData.getFailCount();
                		map.put(month,new ChartData(month,1,"green",chartData.getPassCount(),1));
                	}
            }
			}
		
		months.forEach((key)->{
			if(map.get(key)!=null) {
				charDataResponses.add(new CharDataResponse(key, map.get(key)));
			}else {
				
				ChartData chartData = new ChartData();
				chartData.setDate(key);
				chartData.setColor("green");
				chartData.setTotalCount(0);
				chartData.setPassCount(0);
				chartData.setFailCount(0);
				charDataResponses.add(new CharDataResponse(key, chartData));
			}
		});
		
		System.out.println(charDataResponses);
		return charDataResponses;
	}

	@Override
	public List<Long> getYear() {
		// TODO Auto-generated method stub
		List<Long> year = new ArrayList<>();
		List<Result> allResult= resultRepository.findAll();
		for(Result result:allResult) {
			Long currYear = Long.parseLong(result.getYear());
			if(!year.contains(currYear)) {
				year.add(currYear);
			}
		}
		Collections.sort(year,Collections.reverseOrder());
		return year;
	}

	@Override
	public Result getResultById(int id) {
		return resultRepository.findById(id).get();
	}

	
	

}
