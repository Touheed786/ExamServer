package com.exam.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.User;
import com.exam.model.exam.Result;

public interface ResultRepository extends JpaRepository<Result, Integer> {
	Set<Result> findByUser(User user);
	
	Set<Result> findByYear(String year);
	

}
