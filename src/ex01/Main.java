package ex01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	private static final int NUM_THREADS = 2;
	private static final int NUM_TASKS = 8;
	
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
		
		for (int i = 0; i < NUM_TASKS; i++) {
			pool.execute(new Ex01(i+1));
		}
		
		pool.shutdown();
	}
}
