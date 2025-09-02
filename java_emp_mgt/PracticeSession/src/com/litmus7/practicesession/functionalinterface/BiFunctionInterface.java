package com.litmus7.practicesession.functionalinterface;

@FunctionalInterface
interface Addition<A, B, C> {
	C add(A a, B b);
}

public class BiFunctionInterface {
	public static void main(String[] args) {
		Addition<Integer, Integer, Integer> addNum = (a, b) -> (a + b);
		System.out.println(addNum.add(1, 2));
	}
}
