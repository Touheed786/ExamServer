package com.exam.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;

@RestController
@RequestMapping("/quiz")
//@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = {"http://13.49.19.219", "http://localhost:59537","http://localhost:4200"})
@CrossOrigin(origins = {"http://13.49.19.219", "http://localhost:59537","http://localhost:4200","https://6621634ff050a106eb966a6c--snazzy-marzipan-68443b.netlify.app/"})
public class QuizController {
	
	@Autowired
	private QuizService quizService;
	
//	add Quiz
	@PostMapping("/")
	public ResponseEntity<Quiz> add(@RequestBody Quiz quiz)
	{
		return ResponseEntity.ok(quizService.addQuiz(quiz));
	}
	
//	Update Quiz
	@PutMapping("/")
	public ResponseEntity<Quiz> update(@RequestBody Quiz quiz)
	{
		return ResponseEntity.ok(quizService.updateQuiz(quiz));
	}
	
	
//	get Quizez
	@GetMapping("/")
	public ResponseEntity<Set<Quiz>> getQuizez()
	{
		return ResponseEntity.ok(quizService.getQuizez());
	}
	
//	get Quiz
	@GetMapping("/{qId}")
	public ResponseEntity<Quiz> getQuiz(@PathVariable("qId") Long qId)
	{
		return ResponseEntity.ok(quizService.getQuiz(qId));
	}
	
//	delete Quiz
	@DeleteMapping("/{qId}")
	public void deleteQuiz(@PathVariable("qId")Long qId)
	{
		this.quizService.deleteQuiz(qId);
	}
	
//	get Quizes By Category
	@GetMapping("/category/{cId}")
	public ResponseEntity<?> getQuizesByCategory(@PathVariable("cId") Long cId)
	{
		Category category = new Category();
		category.setCid(cId);
		return ResponseEntity.ok(this.quizService.getQuizesOfCategory(category));
	}
	
//	get All Active Quiz 
	@GetMapping("all/active/")
	public Set<Quiz> getAllActiveQuizes()
	{
		return this.quizService.getAllActiveQuizes();
	}
	
//	get All Active Quiz By Category
	@GetMapping("category/active/{cId}")
	public Set<Quiz> getAllActiveQuizesByCategory(@PathVariable("cId") Long cId)
	{
		Category category = new Category();
		category.setCid(cId);
		return this.quizService.getAllActiveQuizesByCategory(category);
	}
}
