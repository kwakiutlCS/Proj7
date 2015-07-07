package ex06;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Main {
	private static final int NUM_THREADS = 4;
	
	public static void main(String[] args) {
		
		final List<Double> results = new ArrayList<>();
		final CountDownLatch signal = new CountDownLatch(NUM_THREADS);
		
		(new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					signal.await();
				} catch (InterruptedException e) {}
				double total = 0;
				for (int i = 0; i < results.size(); i++) {
					total += results.get(i);
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
