package ex02;

import java.util.concurrent.Semaphore;


public class Ex02 implements Runnable {
	private int taskNumber;
	private static Semaphore[] semaphores = new Semaphore[Main.NUM_TASKS];
	
	static {
		for (int i = 0; i < Main.NUM_TASKS; i++) {
			semaphores[i] = new Semaphore(0);
		}
	}
	
	
	public Ex02(int number) {
		this.taskNumber = number;
	}
	
	public void run() {
		if (taskNumber % 2 == 0 && taskNumber != 2) {
			try {
				semaphores[taskNumber-4].acquire();
			} catch (InterruptedException e) {}
		}
		else if (taskNumber % 2 == 1) {
			try {
				semaphores[taskNumber].acquire();
			} catch (InterruptedException e) {}
		}
		System.out.println("Hello World, I am thread "+Thread.currentThread().getId()+" running task "+taskNumber);
		semaphores[taskNumber-1].release();
		
	}

}
