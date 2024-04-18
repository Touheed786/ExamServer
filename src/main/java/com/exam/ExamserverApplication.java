package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

@SpringBootApplication
public class ExamserverApplication{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
		System.out.println("Application is Started");

	}

//	@Override
//	public void run(String... args) throws Exception {

		
//		User user = new User();
//		user.setFirstName("Allen");
//		user.setLastName("Mac");
//		user.setUsername("admin");
//		user.setPassword(this.bCryptPasswordEncoder.encode("admin"));
//		user.setEmail("admin@gmail.com");
//		user.setProfile("default.png");
//		 
//		Role role1 = new Role();
//		role1.setRoleId(44L);
//		role1.setRoleName("ADMIN");
//		
//		Set<UserRole> userRoleSet = new HashSet<>();
//		UserRole userRole = new UserRole();
//		userRole.setRole(role1);
//		userRole.setUser(user);
//		userRoleSet.add(userRole);
//		
//		User local = userRepository.findByUsername(user.getUsername());
//		if(local == null)
//		{
//			User user1 =  userService.createUser(user,userRoleSet);
//			System.out.println(user1.getUsername());
//		}
		

//	}

}
