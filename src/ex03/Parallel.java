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
		
		
		// generate numbers
		final double[] numbers = Functions.getDoubleArray(size);
		
		
		final long startTime = System.currentTimeMillis();
		ExecutorService pool = Executors.newFixedThreadPool(2);
		
		// calculate max
		Future<Double> maxFuture = pool.submit(() -> {return Functions.getMax(numbers);});
		// calculate min
		Future<Double> minFuture = pool.submit(() -> {return Functions.getMin(numbers);});
		// calculate average
		Future<Double> avgFuture = pool.submit(() -> {return Functions.getAvg(numbers);});
		
		double max = 0, min = 0, avg = 0;
		
		try {
			max = maxFuture.get();
			min = minFuture.get();
			avg = avgFuture.get();
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
