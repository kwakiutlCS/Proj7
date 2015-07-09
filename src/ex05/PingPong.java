package ex05;

import java.util.concurrent.Exchanger;

public class PingPong {
	public static void main(String[] args) {
		
		final Exchanger<Boolean> exchanger = new Exchanger<>();
		
		
		// start other thread
		new Thread(new Runnable() {
			boolean writer = true;
			@Override
			public void run() {
				String s = "ping";
				while (true) {
					if (writer) System.out.println(s);
					try {
						writer = exchanger.exchange(writer);
					} catch (InterruptedException e) {}
				}
			}
		}).start();
		
		String s = "pong";
		boolean writer = false;
		while (true) {
			if (writer) System.out.println(s);
			try {
				writer = exchanger.exchange(writer);
			} catch (InterruptedException e) {}
		}
	}

}
