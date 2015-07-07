package ex03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
		pool.submit(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				double max = Functions.getMax(numbers);
				System.out.println("max -> "+max);
			}
			
		});

		// calculate min
		pool.submit(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				double min = Functions.getMin(numbers);
				System.out.println("min -> "+min);
			}
		});
				
				
		// calculate average
		double avg = Functions.getAvg(numbers);
		
		
		// calculate time
		long finishTime = System.currentTimeMillis();
		
				
		
		System.out.println("avg -> "+avg);
		System.out.println("time -> "+(finishTime-startTime)+" ms");
		
	}

}
