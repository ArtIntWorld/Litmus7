package com.litmus7.practicesession.encapsulation;

public class TestLogin {
	public static void main(String[] args) {
		Login login = new Login();
		login.setName("Abdulla");
		login.setPassword("Abdu12344!");
		
		System.out.println("User : " + login.getName() + "\nPassword : " + login.getPassword());
	}
}
