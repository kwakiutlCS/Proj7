package ex03;

public class Main {
	public static void main(String[] args) {
		int[] points = new int[]{100, 1000, 10000, 100000, 1000000, 10000000, 100000000};

		// sequential
		System.out.println("Sequential");
		for (int i : points) {
			System.out.println(i);
			Sequential.main(new String[]{String.valueOf(i)});
			System.out.println();
		}

		System.out.println();
		System.out.println();
		
		// parallel
		System.out.println("Parallel");
		for (int i : points) {
			System.out.println(i);
			Parallel.main(new String[]{String.valueOf(i)});
			System.out.println();
		}
	}
}
