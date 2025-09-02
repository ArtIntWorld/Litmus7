package com.litmus7.practicesession.multithreading;

public class DaemonUserTHreadExample {
	public static void main(String[] args) {
		Thread bgThread = new Thread(new DaemonHelper());
		Thread userThread = new Thread(new UserThreadHelper());
		
		bgThread.setDaemon(true);
		
		bgThread.start();
		userThread.start();
		try {
			userThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("End of execution");
		
		
	}
}

class DaemonHelper implements Runnable{

	@Override
	public void run() {
		int count = 0;
		while(count<500) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
			System.out.println("Daemon Thread is running...");
		}
		
	}
	
}

class UserThreadHelper implements Runnable{

	@Override
	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("User Thread is executing...");
		
	}
	
}