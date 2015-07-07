package ex04;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private static final int NUM_THREADS = 6;
	private static final int CAPACITY = 5;
	
	ArrayBlockingQueue<Double> queue = new ArrayBlockingQueue<>(CAPACITY);
	
	public void add(double x) {
		queue.add(x);
	}
	
	public Double get() {
		try {
			return queue.take();
		} catch (InterruptedException e) {}
		return null;
	}
	
	
	
	
	public static void main(String[] args) {
		final Server server = new Server();
		ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
		
		// master thread
		pool.submit(new Runnable() {

			@Override
			public void run() {
				Random rand = new Random();
				int max_number = 100;
				
				while (true) {
					server.add(rand.nextDouble()*max_number);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
				}
			}
			
		});
		
		// worker threads
		for (int i = 0; i < NUM_THREADS-1; i++) {
			pool.submit(new Runnable() {

				@Override
				public void run() {
					while (true) {
						double n = server.get();
						System.out.println("Thread "+Thread.currentThread().getId()+" -> square root of "+n+" is "+Math.sqrt(n));
					}
				}
				
			});
		}
	}
}
