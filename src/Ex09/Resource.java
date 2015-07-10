package Ex09;

import java.util.PriorityQueue;

public class Resource {
	private int threadsActive = 0;
	private PriorityQueue<Integer> waitingTasks = new PriorityQueue<>();
	
	public void enter(int priority) {
		synchronized(this) {
			// add task to queue
			waitingTasks.add(priority);
			
			while (threadsActive > 0 || waitingTasks.peek() != priority) {
				try {
					wait();
				} catch (InterruptedException e) {}
			}
			
			// remove task from waiting list and add threads active
			System.out.println("priority list: "+waitingTasks);
			waitingTasks.poll();
			threadsActive++;
			
		}
	}
	
	
	public synchronized void leave() {
		// reduce active thread count and notify others
		threadsActive--;
		notifyAll();
	}
	
	
	
}
