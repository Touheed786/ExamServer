package com.exam.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.exam.service.CategoryService;

@RestController
@RequestMapping("/categories")
//@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = {"http://13.49.19.219", "http://localhost:59537","http://localhost:4200"})
@CrossOrigin(origins = "*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
//	add Category
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category)
	{
		Category category1 = this.categoryService.addCategory(category);
		return ResponseEntity.ok(category1);
		
	}
	
//	get Category
	@GetMapping("/{categoryId}")
	public Category getCategory(@PathVariable("categoryId") Long categoryId)
	{
		return categoryService.getCategory(categoryId);
	}
	
//	get all Categories
//	@GetMapping("/")
//	public Set<Category> getAllCategories()
//	{
//		return categoryService.getCategories();
//	}
	
	@GetMapping("/")
	public ResponseEntity<Set<Category>> getAllCategories()
	{
		return ResponseEntity.ok(categoryService.getCategories());
	}
	
//	Update Category
	@PutMapping("/")
	public Category updateCategory(@RequestBody Category category)
	{
		return this.categoryService.updateCategory(category);
	}
	
//	Delete Category
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable("categoryId") Long categoryId)
	{
		this.categoryService.deleteCategory(categoryId);
	}
}
