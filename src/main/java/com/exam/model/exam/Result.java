package com.exam.model.exam;


import java.util.Date;

import com.exam.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String quiz_type;
	private float marksGot;
	private int correctedAnswer;
	private int attempted;
	private int numberOfQuestion;
	private String date;
	private boolean test_result;
	private String month;
	private String year;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Quiz quiz;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getQuiz_type() {
		return quiz_type;
	}
	public void setQuiz_type(String quiz_type) {
		this.quiz_type = quiz_type;
	}
	
	public float getMarksGot() {
		return marksGot;
	}
	public void setMarksGot(float marksGot) {
		this.marksGot = marksGot;
	}
	public int getCorrectedAnswer() {
		return correctedAnswer;
	}
	public void setCorrectedAnswer(int correctedAnswer) {
		this.correctedAnswer = correctedAnswer;
	}
	public int getAttempted() {
		return attempted;
	}
	public void setAttempted(int attempted) {
		this.attempted = attempted;
	}
	public int getNumberOfQuestion() {
		return numberOfQuestion;
	}
	public void setNumberOfQuestion(int numberOfQuestion) {
		this.numberOfQuestion = numberOfQuestion;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
	public Quiz getQuiz() {
		return quiz;
	}
	
	public boolean getTest_result() {
		return test_result;
	}
	public void setTest_result(boolean test_result) {
		this.test_result = test_result;
	}
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "Result [id=" + id + ", quiz=" + quiz + ", marksGot=" + marksGot + ", correctedAnswer=" + correctedAnswer
				+ ", attempted=" + attempted + ", numberOfQuestion=" + numberOfQuestion + ", user=" + user + "]";
	}
	
}
