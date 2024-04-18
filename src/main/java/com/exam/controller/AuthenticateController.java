package com.exam.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.config.JwtUtil;
import com.exam.model.JwtRequest;
import com.exam.model.User;
import com.exam.service.impl.UserDetailsServiceImp;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = {"http://13.49.19.219", "http://localhost:59537","http://localhost:4200","https://6621634ff050a106eb966a6c--snazzy-marzipan-68443b.netlify.app/"})
//@CrossOrigin(origins = "*")
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImp userDetailsServiceImp;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	// Generate Token
	@PostMapping("/generate-token")
	public ResponseEntity<JwtRequest> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		System.out.println(jwtRequest.getUsername()+" "+jwtRequest.getPassword());
		try {
			this.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		}catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("USER NOT FOUND");
		}
		//////////////authenticate
		UserDetails userDetails = this.userDetailsServiceImp.loadUserByUsername(jwtRequest.getUsername());
		System.out.println("userDetails "+userDetails);
		String token = this.jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtRequest(token));
	}
	
	private void authenticate(String username,String password) throws Exception
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch (DisabledException e) {
			throw new Exception("USER DISABLED " + e.getMessage());
		}catch (BadCredentialsException e) { 
			throw new Exception("Invalid Credential " + e.getMessage());
		}
	}
	
//	Return the current user details
	@GetMapping("/current-user")
	public User getCurrentUSer(Principal principal)
	{
		return (User) this.userDetailsServiceImp.loadUserByUsername(principal.getName());
	}
	
}
