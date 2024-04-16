package com.exam.model;

public class JwtRequest {
	private String username;
	private String password;
	private String token;
	
	public JwtRequest(String token) {
		super();
		this.token = token;
	}

//	public JwtRequest(String username, String password) {
//		super();
//		this.username = username;
//		this.password = password;
//	}
	
	public JwtRequest(String token, String username) {
		// TODO Auto-generated constructor stub
		super();
		this.token = token;
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
