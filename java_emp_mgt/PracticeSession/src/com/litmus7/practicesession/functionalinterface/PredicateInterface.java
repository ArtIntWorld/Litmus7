package com.litmus7.practicesession.functionalinterface;

@FunctionalInterface
interface EvenOdd {
	boolean isEven(Integer num);
}

public class PredicateInterface {

	public static void main(String[] args) {
		
		EvenOdd evenCheck = num -> num%2 == 0;
		
		System.out.println("The number 4 is : " + (evenCheck.isEven(4) ? "Even" : "Odd"));

	}

}
