package com.board;

public class User {
	
	private String user, password;
	
	public User(){
		
	}
	
	public User(String user){
		setUser(user);
	}

	public User(String user, String password){
		setUser(user);
		setPassword(password);
	}
	
	public void setUser(String user){
		this.user = user;
	}

	public String getUser(){
		return user;
	}

	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return password;
	}
	
}
