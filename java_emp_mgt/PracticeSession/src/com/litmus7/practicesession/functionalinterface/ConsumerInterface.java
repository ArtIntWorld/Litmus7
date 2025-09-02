package com.litmus7.practicesession.functionalinterface;

@FunctionalInterface
interface Conversion {
	void toUpper(String str);
	
	default String toLower(String inputStr) {
		return inputStr.toLowerCase();
	}
}

class ConvertSomething implements Conversion {
	
	@Override 
	public void toUpper(String str) {
		System.out.println(str);
	}
	
}

public class ConsumerInterface {
	
	public static void main(String[] args) {
		Conversion convert = (String str) -> System.out.println(str.toUpperCase());
		
		convert.toUpper("one piece");
	}
}
