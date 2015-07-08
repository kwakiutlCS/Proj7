package Ex09;

import java.util.Random;


public class PriorityTask implements Runnable, Comparable<PriorityTask> {
	private int id;
	private Resource resource;
	private int priority;
	
	public PriorityTask(int id, Resource resource) {
		this.id = id;
		this.resource = resource;
	}
	
	
	@Override
	public String toString() {
		return "Task no: "+id+" with priority: "+priority;
	}
	
	@Override
	public int compareTo(PriorityTask o) {
		return o.priority-priority;
	}
	
	@Override
	public void run() {
		Random rand = new Random();
		while (true) {
			priority = rand.nextInt(100);
			resource.enter(this);
		}
	}
}