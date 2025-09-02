package com.litmus7.practicesession.encapsulation;

public class Login {
	private String name;
	private String password;
	
	
	public Login(){ }
	
	public void setName(String name) { this.name = name; }
	public String getName() { return name; }
	
	public void setPassword(String password) { this.password = password; }
	public String getPassword() { return password; }
}
