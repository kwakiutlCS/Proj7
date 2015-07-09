package ex05;

import java.util.concurrent.Exchanger;

public class PingPong {
	public static void main(String[] args) {
		
		final Exchanger<Boolean> exchanger = new Exchanger<>();
		
		
		// start other thread
		new Thread(new Runnable() {
			boolean writing = true;
			@Override
			public void run() {
				String s = "ping";
				while (true) {
					if (writing) System.out.println(s);
					try {
						writing = exchanger.exchange(writing);
					} catch (InterruptedException e) {}
				}
			}
		}).start();
		
		String s = "pong";
		boolean writing = false;
		while (true) {
			if (writing) System.out.println(s);
			try {
				writing = exchanger.exchange(writing);
			} catch (InterruptedException e) {}
		}
	}

}
