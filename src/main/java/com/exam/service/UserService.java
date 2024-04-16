package com.exam.service;

import java.util.Set;

import com.exam.model.User;
import com.exam.model.UserProfileResponse;
import com.exam.model.UserRole;

public interface UserService {
	
//	creating user
	public User createUser(User user,Set<UserRole>userRole) throws Exception;
	
//	get user by username
	public UserProfileResponse getUser(String username);
	
//	delete user by id
	
	public void deleteUser(Long userId);
	
//	Editing User
	public User updateUser(UserProfileResponse profile);
	
	public User getUserById(long userId);
	
	public void initUser() throws Exception;
	
}
