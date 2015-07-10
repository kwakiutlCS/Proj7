package ex08;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
	private static final int NUM_THREADS = 8;
	
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
		
		for (int i = 0; i < NUM_THREADS; i++) {
			pool.execute(new Task(i+1));
		}
		
		pool.shutdown();
	}

}
