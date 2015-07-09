package Ex09;

import java.util.PriorityQueue;

public class Resource {
	private int threadsActive = 0;
	private PriorityQueue<PriorityTask> waitingTasks = new PriorityQueue<>();
	
	public void enter(PriorityTask task) {
		synchronized(this) {
			System.out.println(task+" asked permission to execute function");
			// add task to queue
			waitingTasks.add(task);
			
			while (threadsActive > 0 || !waitingTasks.peek().equals(task)) {
				try {
					wait();
				} catch (InterruptedException e) {}
			}
			
			// remove task from waiting list and add threads active
			waitingTasks.poll();
			threadsActive++;
			System.out.println("\n"+task+" is accessing the resource\n");
		}
		
		execute(task);
		leave(task);
	}
	
	
	private synchronized void leave(PriorityTask task) {
		System.out.println(task+" stopped execution on resource");
		
		// reduce active thread count and notify others
		threadsActive--;
		notifyAll();
	}
	
	
	private void execute(PriorityTask task) {
		// simulating work
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
	}
}
