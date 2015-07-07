package ex06;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class ExA {
	private static final int NUM_THREADS = 4;
	
	public static void main(String[] args) {
		
		final BlockingQueue<Double> results = new ArrayBlockingQueue<>(4);
		final CountDownLatch signal = new CountDownLatch(NUM_THREADS);
		
		(new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					signal.await();
				} catch (InterruptedException e) {}
				
				double total = 0;
				
				while (results.size() > 0) {
					double tmp;
					try {
						tmp = results.take();
						total += tmp;
					} catch (InterruptedException e) {}
				}
				System.out.println(total);
			}
		})).start();
		
		
		for (int i = 0; i < NUM_THREADS; i++) {
			(new Thread(new Runnable() {
				
				@Override
				public void run() {
					Random rand = new Random();
					int max_number = 100;
					double partial = rand.nextDouble()*max_number;
					results.add(partial);
					
					signal.countDown();
				}
				
			})).start();
		}
	}
}
