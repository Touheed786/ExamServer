package com.exam.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserProfileResponse;
import com.exam.model.UserRole;
import com.exam.service.UserService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/user")
//@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = {"http://13.49.19.219", "http://localhost:59537","http://localhost:4200"})
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostConstruct
	public void initConstruct() throws Exception {
		userService.initUser();
	}
	
	//	creating user
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		Set<UserRole> roles = new HashSet<>();
		user.setProfile(user.getProfile());
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");
		
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		roles.add(userRole);
		return this.userService.createUser(user,roles);
	}
	
//	Getting User by userName
	
	@GetMapping("/{username}")
	public UserProfileResponse getUser(@PathVariable("username") String username)
	{
		return this.userService.getUser(username);	
	}
	
//	Deleting User by Id
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId)
	{
		this.userService.deleteUser(userId);
	}
	
//	Update user Details
	
	@PostMapping("/update")
	public User updateUser(@RequestBody UserProfileResponse profile) throws Exception
	{
		System.out.println("Touheed sab");
		return this.userService.updateUser(profile);
	}
	
	@GetMapping("/test")
	public String test()
	{
		return "Welcome to backend Exam Portal";
	}

}
