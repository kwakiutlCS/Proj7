package Ex09;

import java.util.PriorityQueue;

public class Resource {
	private static final int TASKS_MAXIMUM = 10000000;
	private int threadsActive = 0;
	private int nextOrderNo = 1;
	
	private PriorityQueue<PriorityTask> waitingTasks = new PriorityQueue<>();
	
	public void enter(PriorityTask task) {
		synchronized(this) {
			
			// to next order no and resets counter if necessary
			task.setOrderNo(nextOrderNo++);
			nextOrderNo %= TASKS_MAXIMUM;
			System.out.println("Permission: "+task);
			
			// add task to queue
			waitingTasks.add(task);
			
			// tests if thread should wait
			while (threadsActive > 0 || !waitingTasks.peek().equals(task)) {
				try {
					wait();
				} catch (InterruptedException e) {}
			}
			
			// remove task from waiting list and add threads active
			waitingTasks.poll();
			threadsActive++;
			
			System.out.println("Access: "+task+"\n");
		}
		
		
	}
	
	
	public synchronized void leave() {
		// reduce active thread count and notify others
		threadsActive--;
		notifyAll();
	}
}
