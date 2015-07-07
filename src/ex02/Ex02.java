package ex02;

import java.util.concurrent.Semaphore;


public class Ex02 implements Runnable {
	private int number;
	private static Semaphore[] semaphores = new Semaphore[Main.NUM_TASKS];
	
	static {
		for (int i = 0; i < Main.NUM_TASKS; i++) {
			semaphores[i] = new Semaphore(0);
		}
	}
	
	public Ex02(int number) {
		this.number = number;
	}
	
	public void run() {
		if (number % 2 == 0 && number != 2) {
			try {
				semaphores[number-4].acquire();
			} catch (InterruptedException e) {}
		}
		else if (number % 2 == 1) {
			try {
				semaphores[number].acquire();
			} catch (InterruptedException e) {}
		}
		System.out.println("Hello World, I am thread "+Thread.currentThread().getId()+" running task "+number);
		semaphores[number-1].release();
	}

}
