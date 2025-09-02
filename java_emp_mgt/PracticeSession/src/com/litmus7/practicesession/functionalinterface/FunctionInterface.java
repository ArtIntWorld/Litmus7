package com.litmus7.practicesession.functionalinterface;

@FunctionalInterface
interface Mathematics1<T, R>{
	T square(R num);
}


public class FunctionInterface {
	
	public static void main(String[] args) {
		Mathematics1<Integer, Integer> maths1 = num -> (num*num);
		
		System.out.println(maths1.square(4));
	}
}
