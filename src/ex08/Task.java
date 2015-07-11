package ex08;

public class Task implements Runnable {
	private static final int TASKS_MAXIMUM = 10000000;
	private static int threadsActive = 0;
	private static int nextOrderNo = 1;
	private static int nextTask = 1;
	private static Object sync = new Object();
	
	private int id;
	private int orderNo;
	
	public Task(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Task no: "+id;
	}

	@Override
	public void run() {
		while (true) {
			enter();
		}
	}
	
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
	public int getOrderNo() {
		return orderNo;
	}
	
	
	
	private void enter() {
		synchronized(sync) {
			// to next order no and resets counter if necessary
			setOrderNo(nextOrderNo++);
			nextOrderNo %= TASKS_MAXIMUM;
			System.out.println(this+" asked permission to execute function");
			while (threadsActive > 0 || this.getOrderNo() != nextTask) {
				try {
					sync.wait();
				} catch (InterruptedException e) {}
			}
			// adds to next task and resets counter if necessary
			nextTask++;
			nextTask %= TASKS_MAXIMUM;
			threadsActive++;
			System.out.println("\n"+this+" is accessing the resource\n");
		}
		
		execute();
		leave();
	}
	
	
	private void leave() {
		synchronized(sync) {
			System.out.println(this+" stopped execution on resource");
			threadsActive--;
			sync.notifyAll();
		}		
	}
	
	// critical region
	private void execute() {
		// simulating work
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 3765bce0f5c39c819f976636fa3a9e43523af430
