package com.litmus7.practicesession.multithreading;

public class SynchronizationExample {
	
	private static int counter = 0;
	
	public static void main(String[] args) {
		
		Thread oneNew = new Thread(() -> {
			for(int i = 0; i<50000; i++) {
				counter++;
			}
		});
		
		Thread twoNew = new Thread(() -> {
			for(int i = 0; i<50000; i++) {
				counter++;
			}
		});
		
		oneNew.start();
		twoNew.start();
		
		try {
			oneNew.join();
			twoNew.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Counter value : " + counter);
		
	}
}
