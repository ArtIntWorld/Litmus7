package com.litmus7.practicesession.multithreading;

public class JoinThreadExample {
	public static void main(String[] args) {
		Thread one = new Thread(() -> {
			for(int i=0; i<15;i++) {
				System.out.println("Thread 1 : " + i);
			}
		});
		
		Thread two = new Thread(() -> {
			for(int i=0; i<15;i++) {
				System.out.println("Thread 2 : " + i);
			}
		});
		System.out.println("Before executing the thread...");
		one.start();
		two.start();
		try {
			one.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done executing the thread!");
	}
}
