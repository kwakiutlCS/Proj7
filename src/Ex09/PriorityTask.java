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
			priority = rand.nextInt(10);
			resource.enter(this);
			execute();
			resource.leave();
		}
	}


	public int getPriority() {
		return priority;
	}
	
	private void execute() {
		// simulating work
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
	}


	public int getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
}
