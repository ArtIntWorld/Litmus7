package com.litmus7.practicesession.inheritance;

class Animal {
	
	void kingdom() {
		System.out.println("It\'s an animal kingdom.");
	}
}

class Tiger extends Animal{
	void sound() {
		System.out.println("Roarrrrrrrrr!");
	}
}

public class SingleInheritance {
	
	public static void main(String[] args) {
		Tiger tiger = new Tiger();
		tiger.kingdom();
		tiger.sound();
	}
}
