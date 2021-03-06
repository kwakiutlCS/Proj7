package ex08;


public class Resource {
	private static final int TASKS_MAXIMUM = 20000000;
	private int threadsActive = 0;
	private static int nextOrderNo = 1;
	private static int nextTask = 1;
	
	public void enter(Task task) {
		synchronized(this) {
			// to next order no and resets counter if necessary
			task.setOrderNo(nextOrderNo++);
			nextOrderNo %= TASKS_MAXIMUM;
			System.out.println(task+" asked permission to execute function");
			while (threadsActive > 0 || task.getOrderNo() != nextTask) {
				try {
					wait();
				} catch (InterruptedException e) {}
			}
			// adds to next task and resets counter if necessary
			nextTask++;
			nextTask %= TASKS_MAXIMUM;
			threadsActive++;
			System.out.println("\n"+task+" is accessing the resource\n");
		}
		
		execute(task);
		leave(task);
	}
	
	
	private synchronized void leave(Task task) {
		System.out.println(task+" stopped execution on resource");
		threadsActive--;
		
		notifyAll();
	}
	
	// critical region
	private void execute(Task task) {
		// simulating work
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
	}
}
