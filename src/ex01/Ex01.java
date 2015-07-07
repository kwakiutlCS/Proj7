package ex01;



public class Ex01 implements Runnable {
	private int number;
	
	public Ex01(int number) {
		this.number = number;
	}
	
	public void run() {
		System.out.println("Hello World, I am thread "+Thread.currentThread().getId()+" running task "+number);
	}

}
