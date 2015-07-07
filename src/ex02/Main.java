package ex02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




public class Main {
	static final int NUM_THREADS = 8;
	static final int NUM_TASKS = 8;
	
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
		
		for (int i = 0; i < NUM_TASKS; i++) {
			pool.execute(new Ex02(i+1));
		}
		
		pool.shutdown();
	}
}


