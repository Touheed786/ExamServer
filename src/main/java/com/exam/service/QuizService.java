package com.exam.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.exam.model.exam.Category;
import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;

public interface QuizService {

	public Quiz addQuiz(Quiz quiz);
	
	public Quiz updateQuiz(Quiz quiz);
	
	public Set<Quiz> getQuizez();
	
	public Quiz getQuiz(Long quizId);
	
	public void deleteQuiz(Long quizId);
	
	public Set<Quiz> getQuizesOfCategory(Category category);
	
	public Set<Quiz> getAllActiveQuizes();
	
	public Set<Quiz> getAllActiveQuizesByCategory(Category category);


}
