package ex05;

import java.util.concurrent.Exchanger;

public class PingPong {
	public static void main(String[] args) {
		
		final Exchanger<String> exchanger = new Exchanger<>();
		
		// start other thread
		new Thread(new Runnable() {
			@Override
			public void run() {
				String s = "ping";
				while (true) {
					System.out.println(s);
					try {
						s = exchanger.exchange(s);
					} catch (InterruptedException e) {}
				}
			}
		}).start();
		
		String s = "pong";
		while (true) {
			System.out.println(s);
			try {
				s = exchanger.exchange(s);
			} catch (InterruptedException e) {}
		}
	}

}
