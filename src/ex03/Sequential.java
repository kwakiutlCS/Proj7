package ex03;


public class Sequential {
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
		double[] numbers = Functions.getDoubleArray(size);
		
		
		long startTime = System.currentTimeMillis();
		
		// calculate average
		double avg = Functions.getAvg(numbers);
		
		// calculate max
		double max = Functions.getMax(numbers);
		
		// calculate min
		double min = Functions.getMin(numbers);
		
		long finishTime = System.currentTimeMillis();
		
		System.out.println("avg -> "+avg);
		System.out.println("max -> "+max);
		System.out.println("min -> "+min);
		System.out.println("time -> "+(finishTime-startTime)+" ms");

	}

}
