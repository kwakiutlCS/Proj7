package ex03;

import java.util.Random;

public class Functions {
	
	public static double[] getDoubleArray(int size) {
		int maxNumber = 1;
		double[] numbers = new double[size];
		Random rand = new Random();
		
		for (int i = 0; i < size; i++) {
			numbers[i] = rand.nextDouble()*maxNumber;
		}
		
		return numbers;
	}
	
	public static double getAvg(double[] numbers) {
		double total = 0;
		
		for (int i = 0; i < numbers.length; i++) {
			total += numbers[i];
		}
		
		return total/numbers.length;
	}
	
	public static double getMax(double[] numbers) {
		double max = Double.MIN_VALUE;
		
		for (int i = 0; i < numbers.length; i++) {
			max = Math.max(max, numbers[i]);
		}
		
		return max;
	}
	
	public static double getMin(double[] numbers) {
		double min = Double.MAX_VALUE;
		
		for (int i = 0; i < numbers.length; i++) {
			min = Math.min(min, numbers[i]);
		}
		
		return min;
	}
}
