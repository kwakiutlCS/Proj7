package ex03;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Parallel {
	public static void main(String[] args) {
		if (args.length != 1) return;
		
		int size;
		try {
			size = Integer.parseInt(args[0]);
		}
		catch(NumberFormatException e) {
			return;
		}
		
		ExecutorService pool = Executors.newFixedThreadPool(2);
		
		// generate numbers
		final double[] numbers = Functions.getDoubleArray(size);
		
		
		final long startTime = System.currentTimeMillis();
		
		// calculate max
		Future<Double> maxFuture = pool.submit(new Callable<Double>() {

			@Override
			public Double call() {
				// TODO Auto-generated method stub
				return Functions.getMax(numbers);
			}
			
		});

		// calculate min
		Future<Double> minFuture = pool.submit(new Callable<Double>() {
			@Override
			public Double call() {
				// TODO Auto-generated method stub
				return Functions.getMin(numbers);
			}
		});
				
				
		// calculate average
		double avg = Functions.getAvg(numbers);
		double max = 0, min = 0;
		
		try {
			max = maxFuture.get();
			min = minFuture.get();
		} catch (InterruptedException | ExecutionException e) {}
		
		pool.shutdown();
		
		// calculate time
		long finishTime = System.currentTimeMillis();
		
				
		
		System.out.println("avg -> "+avg);
		System.out.println("max -> "+max);
		System.out.println("min -> "+min);
		System.out.println("time -> "+(finishTime-startTime)+" ms");
		
	}

}
