package ex06;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class ExB {
	private static final int NUM_THREADS = 4;
	
	public static void main(String[] args) {
		
		final BlockingQueue<Double> results = new ArrayBlockingQueue<>(4);
		final CountDownLatch finishSignal = new CountDownLatch(NUM_THREADS);
		final CountDownLatch startSignal = new CountDownLatch(1);
		
		(new Thread(new Runnable() {

			@Override
			public void run() {
				// signaling others thread
				startSignal.countDown();
				
				// waiting for other threads
				try {
					finishSignal.await();
				} catch (InterruptedException e) {}
				
				double total = 0;
				
				while (results.size() > 0) {
					double tmp;
					try {
						tmp = results.take();
						total += tmp;
					} catch (InterruptedException e) {}
				}
				System.out.println("sum "+total);
			}
		})).start();
		
		
		for (int i = 0; i < NUM_THREADS; i++) {
			(new Thread(new Runnable() {
				
				@Override
				public void run() {
					// waiting for single thread
					try {
						startSignal.await();
					} catch (InterruptedException e) {}
					
					Random rand = new Random();
					int max_number = 100;
					double partial = rand.nextDouble()*max_number;
					results.add(partial);
					System.out.println("partial "+partial);
					
					// signaling single thread
					finishSignal.countDown();
				}
				
			})).start();
		}
	}
}
