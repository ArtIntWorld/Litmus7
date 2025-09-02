package com.litmus7.practicesession.anonymousclass;

class A {
	void show() {
		System.out.println("This is A show");
	}
}

class B extends A {
	
	@Override
	void show() {
		System.out.println("This is B show");
	}
}

public class AnonymousClass {

	public static void main(String[] args) {


		B b= new B() {
			void show() {
				System.out.println("This is rare show");
			}
		};
		
		b.show();

	}

}
