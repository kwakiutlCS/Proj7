package Ex09;

import java.util.PriorityQueue;

public class Resource {
	private static final int TASKS_MAXIMUM = 10000000;
	private int threadsActive = 0;
<<<<<<< HEAD
	private int nextOrderNo = 1;
	
	private PriorityQueue<PriorityTask> waitingTasks = new PriorityQueue<>();
=======
	private PriorityQueue<Integer> waitingTasks = new PriorityQueue<>();
>>>>>>> 3765bce0f5c39c819f976636fa3a9e43523af430
	
	public void enter(int priority) {
		synchronized(this) {
<<<<<<< HEAD
			
			// to next order no and resets counter if necessary
			task.setOrderNo(nextOrderNo++);
			nextOrderNo %= TASKS_MAXIMUM;
			System.out.println("Permission: "+task);
			
=======
>>>>>>> 3765bce0f5c39c819f976636fa3a9e43523af430
			// add task to queue
			waitingTasks.add(priority);
			
<<<<<<< HEAD
			// tests if thread should wait
			while (threadsActive > 0 || !waitingTasks.peek().equals(task)) {
=======
			while (threadsActive > 0 || waitingTasks.peek() != priority) {
>>>>>>> 3765bce0f5c39c819f976636fa3a9e43523af430
				try {
					wait();
				} catch (InterruptedException e) {}
			}
			
			// remove task from waiting list and add threads active
			System.out.println("priority list: "+waitingTasks);
			waitingTasks.poll();
			threadsActive++;
			
<<<<<<< HEAD
			System.out.println("Access: "+task+"\n");
		}
		
		
=======
		}
>>>>>>> 3765bce0f5c39c819f976636fa3a9e43523af430
	}
	
	
	public synchronized void leave() {
		// reduce active thread count and notify others
		threadsActive--;
		notifyAll();
	}
<<<<<<< HEAD
=======
	
	
	
>>>>>>> 3765bce0f5c39c819f976636fa3a9e43523af430
}
