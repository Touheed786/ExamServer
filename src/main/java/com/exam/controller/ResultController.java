package com.exam.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.User;
import com.exam.model.exam.CharDataResponse;
import com.exam.model.exam.Result;
import com.exam.repo.ResultRepository;
import com.exam.service.ResultService;

@RestController
@RequestMapping("/result")
//@CrossOrigin(origins = {"http://13.49.19.219", "http://localhost:59537","http://localhost:4200"})
@CrossOrigin(origins = "*")
public class ResultController {
	
	@Autowired
	private ResultService resultService;
	
	@PostMapping("/")
	public Result addResult(@RequestBody Result result)
	{
		return resultService.addResult(result);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getResults()
	{
		return ResponseEntity.ok(resultService.gellAllResult());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getResultByUser(@PathVariable("userId") Long userId)
	{
		User user = new User();
		user.setId(userId);
		Set<Result> result = resultService.getResultByUser(user);

		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/getChartData/{year}")
	public List<CharDataResponse> getChartData(@PathVariable("year") String year){
		return this.resultService.getChartData(year);
	}
	
	@GetMapping("/year")
	public List<Long> getYear(){
		return resultService.getYear();
	}
	
	@GetMapping("/getResult/{id}")
	public Result getResult(@PathVariable("id") int id) {
		return resultService.getResultById(id);
	}
	
}
