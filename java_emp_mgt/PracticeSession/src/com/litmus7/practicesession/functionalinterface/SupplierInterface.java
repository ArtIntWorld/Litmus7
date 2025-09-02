package com.litmus7.practicesession.functionalinterface;

@FunctionalInterface
interface RandomNumbers {
	double generate();
}

public class SupplierInterface {
	public static void main(String[] args) {
		RandomNumbers randomGen = () -> Math.random();
		
		System.out.println(randomGen.generate());
	}
}
