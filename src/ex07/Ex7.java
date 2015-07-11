package ex07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Ex7 {
	private static final int NUM_THREADS = 4;
	
	public static void main(String[] args) {
		
		final CyclicBarrier barrier = new CyclicBarrier(4, () -> System.out.println("Barrier"));
		
		
		for (int i = 0; i < NUM_THREADS; i++) {
			(new Thread(new Runnable() {
				
				@Override
				public void run() {
					Random rand = new Random();
					int max_number = 100;
					
					while (true) {
					double total = 0;
					
					for (int i = 0; i < 10; i++) {
						total += rand.nextDouble()*max_number;
					}
					System.out.println(Thread.currentThread().getId()+" -> "+total);
					
					try {
						barrier.await();
					} catch (InterruptedException | BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
				}
				
			})).start();
		}
	}
}
