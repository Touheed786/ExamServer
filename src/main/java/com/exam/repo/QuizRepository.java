package com.exam.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;

import com.exam.model.exam.Category;
import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;

//import jakarta.transaction.Transactional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
//	using deleteById methods its not deleting so tried this way 
//	@Modifying
//    @Transactional
//	@Query(value = "DELETE FROM quiz WHERE q_id = ?1", nativeQuery = true)
//	public void deletedQuiz(Long qid);
	
	Set<Quiz> findByCategory(Category category);
	
	Set<Quiz>findByActive(boolean b);
	
	Set<Quiz>findByActiveAndCategory(boolean b,Category category);
}

