package Ex09;

import java.util.Random;


public class PriorityTask implements Runnable, Comparable<PriorityTask> {
	private int id;
	private Resource resource;
	private int priority;
	private int orderNo;
	
	public PriorityTask(int id, Resource resource) {
		this.id = id;
		this.resource = resource;
	}
	
	
	@Override
	public String toString() {
		return "Task no: "+id+" with priority: "+priority+" and order no "+orderNo;
	}
	
	@Override
	public int compareTo(PriorityTask o) {
		if (o.priority > priority) return 1;
		if (o.priority < priority) return -1;
		if (o.orderNo < orderNo) return 1;
		if (o.orderNo > orderNo) return -1;
		return 0;
	}
	
	@Override
	public void run() {
		Random rand = new Random();
		while (true) {
			// generates a random priority (higher is more important)
<<<<<<< HEAD
			priority = rand.nextInt(10);
			resource.enter(this);
=======
			priority = rand.nextInt(100);
			resource.enter(priority);
>>>>>>> 3765bce0f5c39c819f976636fa3a9e43523af430
			execute();
			resource.leave();
		}
	}
<<<<<<< HEAD


	public int getPriority() {
		return priority;
	}
=======
	
>>>>>>> 3765bce0f5c39c819f976636fa3a9e43523af430
	
	private void execute() {
		// simulating work
		try {
<<<<<<< HEAD
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
	}


	public int getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
=======
			System.out.println("\n"+this+" is accessing the resource\n");
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
	}
	
	
	public int getPriority() {
		return priority;
>>>>>>> 3765bce0f5c39c819f976636fa3a9e43523af430
	}
}
