package com.exam.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserProfileResponse;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EmailService emailService;
	
//	creating user
	@Override
	public User createUser(User user, Set<UserRole> userRole) throws Exception {
		User local = userRepository.findByUsername(user.getUsername());
		if( local != null)
		{
			System.out.println("User is already there");
			throw new Exception("User Already Present!!");
		}else {
//			create user 
			for(UserRole ur:userRole) {
				roleRepository.save(ur.getRole());
			}
			user.getUserRole().addAll(userRole);
			local = this.userRepository.save(user);
		}
	return local;
	}

	@Override
	public UserProfileResponse getUser(String username) {
		// TODO Auto-generated method stub
		User user =  this.userRepository.findByUsername(username);
		
		UserProfileResponse profile = new UserProfileResponse();
		profile.setId(user.getId());
		profile.setFirstName(user.getFirstName());
		profile.setLastName(user.getLastName());
		profile.setUsername(user.getUsername());
//		profile.setPassword(user.getPassword());
		profile.setProfile(user.getProfile());
		profile.setEnable(user.isEnable());
		profile.setPhone(user.getPhone());
		profile.setEmail(user.getEmail());
		
		return profile;
	}

	@Override
	public void deleteUser(Long userId) {
		this.userRepository.deleteById(userId);
		
	}

	@Override
	public User updateUser(UserProfileResponse profile) {
		User userDetails = userRepository.findById(profile.getId()).get();
		
		userDetails.setId(profile.getId());
		userDetails.setUsername(profile.getUsername());
		userDetails.setEmail(profile.getEmail());
//		userDetails.setPassword(this.bCryptPasswordEncoder.encode(profile.getPassword()));
		userDetails.setFirstName(profile.getFirstName());
		userDetails.setLastName(profile.getLastName());
		userDetails.setPhone(profile.getPhone());
		userDetails.setProfile(profile.getProfile());
		
		return this.userRepository.save(userDetails);
	}

	@Override
	public User getUserById(long userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId).get();
	}
	
	@Override
	public void initUser() throws Exception {
		System.out.println("Initializing the Data for User");
		User user = new User();
		user.setFirstName("Allen");
		user.setLastName("Mac");
		user.setUsername("admin");
		user.setPassword(this.bCryptPasswordEncoder.encode("admin"));
//		user.setEmail("admin@gmail.com");
		user.setProfile("default.png");
		 
		Role role1 = new Role();
		role1.setRoleId(44L);
		role1.setRoleName("ADMIN");
		
		Set<UserRole> userRoleSet = new HashSet<>();
		UserRole userRole = new UserRole();
		userRole.setRole(role1);
		userRole.setUser(user);
		userRoleSet.add(userRole);
		
		List<User> local = userRepository.findAll();
		if(local.isEmpty())
		{
			User user1 =  createUser(user,userRoleSet);
			System.out.println(user1.getUsername());
		}
	}
	
	

}
